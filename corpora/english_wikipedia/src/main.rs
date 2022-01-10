#![feature(iter_advance_by)]
use std::fs::File;
use std::io::{self, prelude::*, BufReader};
use rand::prelude::*;
use std::collections::HashSet;
use std::vec::Vec;
use std::convert::TryInto;
use serde::{Serialize, Deserialize};
use serde_json;
use statrs::statistics::Statistics;

#[derive(Deserialize)]
struct JsonEntry {
    body: String,
}

#[derive(Serialize,Deserialize)]
struct JsonFullFile {
    data: Vec<f64>
}

fn main() -> io::Result<()> {
    let args: Vec<String> = std::env::args().collect();
    if args.len() < 2 {
        panic!("Please add a sample size for the command line usage!");
    }
    let sample_size: usize = args.get(1).unwrap().parse::<usize>().unwrap();
    let file = File::open("data")?;
    let reader = BufReader::new(file);

    let lines: u64 = reader.lines().count().try_into().unwrap();
    let mut rng = rand::thread_rng();
    
    let mut next : HashSet<u64> = HashSet::with_capacity(sample_size);
    loop {
        let next_line = rng.next_u64()%lines;
        if next.get(&next_line).is_none() {
            next.insert(next_line);
            if next.len() == sample_size {
                break;
            }
        }
    }
    println!("{}",next.len());

    let file2 = File::open("data")?;
    let reader2 = BufReader::new(file2);

    println!("Fetched the rows of data, serializing now to disk!");

    let mut counter = 0;
    let mut full_vec : Vec<f64> = Vec::new();
    let mut sample_vec: Vec<f64> = Vec::new();

    for x in reader2.lines() {
        let value : JsonEntry = serde_json::from_str(&x.unwrap()).unwrap();
        if value.body.len() != 0 {
            full_vec.push(value.body.len() as f64);
        }
        if next.contains(&counter) {
            if value.body.len() != 0 && sample_vec.len() < sample_size {
                sample_vec.push(value.body.len() as f64);
                std::fs::write(String::from("output/")+&counter.to_string()+".txt", value.body).unwrap();
            }
            else if value.body.len() == 0 {
                let mut next_elem = counter+1;
                loop {
                    if next.contains(&next_elem) {
                        next_elem+=1;
                    }
                    else {
                        break;
                    }
                }
                next.insert(next_elem);
            }
        }
        counter+=1;
    }

    println!("Sample mean {} and variance {}",(&sample_vec).mean(), (&sample_vec).variance());
    println!("Full corpus mean {} and variance {}",(&full_vec).mean(), (&full_vec).variance());
    let val = JsonFullFile{ data: full_vec };
    std::fs::write("output/full_text_lengths.json",serde_json::to_string(&val).unwrap()).unwrap();
    let val_sample = JsonFullFile{ data: sample_vec };
    std::fs::write("output/sample_text_lengths.json",serde_json::to_string(&val_sample).unwrap()).unwrap();

    Ok(())
}
