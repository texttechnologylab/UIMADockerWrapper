import 'dart:convert';
import 'dart:math';
import 'dart:html' as html;

import 'package:container_explorer/bloc/resources_bloc/resources_bloc.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_event.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_state.dart';
import 'package:container_explorer/presentation/nav_drawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';


class ResourcesWidget extends StatelessWidget {
  const ResourcesWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<ResourcesBloc,ResourcesState>(builder: (ctx,state) {
      return state.map(
      initial: (a) {
        ctx.read<ResourcesBloc>().add(const ResourcesEvent.loadTypesystem());
        return const Text('Initial state');
      },
      loading: (a) {
        return const Center(child: CircularProgressIndicator());
      },
      failure: (a) {
        return const Text('There was an error!',style: TextStyle(color: Colors.red),);
      }, 
      loaded: (e) {
      print(e);
        return Center(child: Container(margin: const EdgeInsets.only(top: 10), width: 600, child: ListView(
          children:
            e.res.keys.map((key) {
              var type = key.substring(0,key.indexOf(':')); 
              var icon = const Icon(Icons.file_copy);
              if(type == 'zip') {
                icon = const Icon(Icons.archive);
              }
              var cleanName = key.substring(key.indexOf(':')+1);
              return ListTile(leading: icon,title: Text(key.substring(key.indexOf(':')+1)), subtitle: Text(type),trailing: IconButton(onPressed: (){
                    var blob = html.Blob([e.res[key].toString()], 'text/plain', 'native');
                    if (type != 'string') {
                      blob = html.Blob([base64Decode(e.res[key])], 'application/octet-stream', 'native');
                    }

                    if(!cleanName.endsWith('zip') && type == 'zip') {
                      cleanName+='.zip';
                    }

                    var anchorElement = html.AnchorElement(
                        href: html.Url.createObjectUrlFromBlob(blob).toString(),
                        )..setAttribute("download", cleanName)..click();
              }, icon: const Icon(Icons.download)),);
            }).toList())
        ));
      });
    });
  }
}

class ResourcesScreen extends StatelessWidget {
  const ResourcesScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: const NavDrawer(),
        backgroundColor: Colors.white,
        appBar: AppBar(
          iconTheme: const IconThemeData(color: Colors.white),
          backgroundColor: Colors.black87,
          centerTitle: true,
          title: const Text(
            'Reproducible UIMA Annotations',
            style: TextStyle(color: Colors.white),
          ),
        ),
        body: BlocProvider(
            create: (c) => ResourcesBloc(url: html.window.location.hostname ?? '127.0.0.1', port: 9714),
            child: const ResourcesWidget()));
  }
}
