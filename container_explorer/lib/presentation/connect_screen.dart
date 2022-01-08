import 'package:container_explorer/bloc/url_event.dart';
import 'package:container_explorer/bloc/url_selector.dart';
import 'package:container_explorer/bloc/url_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:container_explorer/mobile_version.dart' if (dart.library.html) 'package:container_explorer/html_version.dart';

class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);

  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
    final TextEditingController _urlController = TextEditingController();
    final TextEditingController _portController = TextEditingController();
    @override
    void initState() {
      super.initState();
      var url = get_url();
      String? port;
      if(url != null) {
        port = get_port().toString();
      }
      _urlController.text = url??'';
      _portController.text = port??'';
    }

    @override
    Widget build(BuildContext context) {
      var url = get_url();
      String? port;
      if(url != null) {
        port = get_port().toString();
        context.read<UrlSelector>().add(UrlEventLoaded(_urlController.text, int.parse(_portController.text))); 
      }

      return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          iconTheme: IconThemeData(color: Colors.white),
          backgroundColor: Colors.black87,
          centerTitle: true,
          title: const Text(
            'UIMADockerWrapper',
            style: TextStyle(fontSize: 26.0, color: Colors.white),
          ),
        ),
        body: BlocBuilder<UrlSelector,UrlState>(builder:
      (ctx,state) {
      return state.map(initial: (v) {
      return Center(child: SizedBox(width: 320, child: Column(
      children: [
        SizedBox(height: 40),
      TextField(  decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter the container url',
          ),
          controller: _urlController,
          ),
        SizedBox(height: 20),
      TextField(  decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter the container port',
          ),
          controller: _portController,
          ),
        SizedBox(height: 20),
          ElevatedButton(onPressed: (){
            ctx.read<UrlSelector>().add(UrlEventLoaded(_urlController.text, int.parse(_portController.text))); 
          }, child: const Text('Connect')),
      ]
      ))); 
      },
      error: (e) {
      return Center(child: SizedBox(width: 320, child: Column(
      children: [
      SizedBox(height: 40),
      TextField(  decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter the container url',
          ),
          controller: _urlController,
          ),
      SizedBox(height: 20),
      TextField(  decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter the container port',
          ),
          controller: _portController,
          ),
        SizedBox(height: 20),
          ElevatedButton(onPressed: (){
            ctx.read<UrlSelector>().add(UrlEventLoaded(_urlController.text, int.parse(_portController.text))); 
          }, child: const Text('Connect')),
        SizedBox(height: 10),
        Text("Could not connect to http://"+e.url+":"+e.port.toString(),style: TextStyle(color: Colors.red)),
        SizedBox(height: 5),
        Text("Error: "+e.error,style: TextStyle(color: Colors.red)),
      ]
      ))); 
      },
      loaded: (r) {
        Navigator.pushReplacementNamed(context, '/');
        return const Text('');
      });
      }));
    }
}
