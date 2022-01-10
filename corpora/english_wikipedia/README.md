# Collecting a sample from the english wikipedia

The file must get downloaded from here ![here](https://www.dropbox.com/s/wwnfnu441w1ec9p/wiki-articles.json.bz2?dl=0). After downloading and extracting the file, save the data file into the folder containing this README.

```
mkdir output
cargo run --release -- 100000
```
The above command will create a sample of 100000 articles from the english wikipedia corpus and save it in the txt format in the folder output. Two metadata files are in this folder too: full_text_lengths.json and sample_text_lengths.txt which contains a list of the text lengths of the full english wikipedia corpus and the sample wikipedia corpus.

