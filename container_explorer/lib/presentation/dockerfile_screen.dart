import 'dart:math';
import 'package:container_explorer/bloc/dockerfile_bloc/dockerfile_bloc.dart';
import 'package:container_explorer/bloc/dockerfile_bloc/dockerfile_event.dart';
import 'package:container_explorer/bloc/dockerfile_bloc/dockerfile_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import './nav_drawer.dart';
import 'package:highlight_text/highlight_text.dart';
import 'package:flutter/services.dart';
import 'dart:html' as html;

class DockerfileWidget extends StatelessWidget {
  const DockerfileWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Map<String, HighlightedWord> words = {
      "RUN": HighlightedWord(
      onTap: (){},
        textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
      ),
      "CMD": HighlightedWord(
          onTap: () {
          },
          textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
),
      "ENTRYPOINT": HighlightedWord(
          onTap: () {
          },
textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
),
      "ADD": HighlightedWord(
          onTap: () {
          },
textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
),
      "ENV": HighlightedWord(
          onTap: () {
          },
textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
),
      "FROM": HighlightedWord(
          onTap: () {
          },
textStyle: const TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
),
    };
    return BlocBuilder<DockerfileBloc,DockerfileState>(builder: (ctx,state) {
      return state.map(
      initial: (a) {
        ctx.read<DockerfileBloc>().add(const DockerfileEvent.loadDockerfile());
        return const Text('Initial state');
      },
      loading: (a) {
        return const Center(child: CircularProgressIndicator());
      },
      failure: (a) {
        return const Text('There was an error!',style: TextStyle(color: Colors.red),);
      }, 
      loaded: (e) {
        return 
        Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [Container(
          width: max(MediaQuery.of(context).size.width/2,min(600,MediaQuery.of(context).size.width-32)),
          padding: const EdgeInsets.all(16),
          margin: const EdgeInsets.all(16),
          decoration: BoxDecoration(color: const Color(0xFFEEEEEE),borderRadius: const BorderRadius.all(Radius.circular(10)),border: Border.all()),
          child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [Row(children: [const Expanded(child:Text('Dockerfile explorer',style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold))), IconButton(onPressed: (){
            Clipboard.setData(ClipboardData(text: e.dockerfile));
            ScaffoldMessenger.of(context).showSnackBar(const SnackBar(backgroundColor: Colors.green,duration: Duration(seconds: 3),content: Text('Added the dockerfile to the system clipboard!')));
          }, icon: const Icon(Icons.copy))]),
          const Divider(color: Colors.black),
          SingleChildScrollView(
              scrollDirection: Axis.horizontal,
      child: TextHighlight(
        words: words,
        text: e.dockerfile,
        textStyle: const TextStyle(color: Colors.black87, fontSize: 16),
      )
      )]))]);
      });
    });
  }
}

class DockerfileScreen extends StatelessWidget {
  const DockerfileScreen({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    return Scaffold(
    drawer: const NavDrawer(),
    backgroundColor: Colors.white,
    appBar: AppBar(
      iconTheme: const IconThemeData(color: Colors.white),
      backgroundColor: Colors.black87,
      centerTitle: true,
      title: const Text('Dockerfile explorer', style: TextStyle(color: Colors.white),),
    ),
    body: BlocProvider(create: (c) => DockerfileBloc(url: html.window.location.hostname ?? '127.0.0.1', port: 9714),
    child: const DockerfileWidget())
    );
  }}
