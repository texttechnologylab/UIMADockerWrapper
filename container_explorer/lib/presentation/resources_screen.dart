import 'dart:convert';
import 'dart:typed_data';

import 'package:container_explorer/bloc/resources_bloc/resources_bloc.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_event.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_state.dart';
import 'package:container_explorer/bloc/url_event.dart';
import 'package:container_explorer/bloc/url_selector.dart';
import 'package:container_explorer/presentation/nav_drawer.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:file_saver/file_saver.dart';


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
              return ListTile(leading: icon,title: Text(key.substring(key.indexOf(':')+1)), subtitle: Text(type),trailing: IconButton(onPressed: () async {
                    Uint8List blob;
                    if (type != 'string') {
                      blob = base64Decode(e.res[key]);
                    }
                    else {
                      List<int> list = utf8.encode(e.res[key]);
                      blob = Uint8List.fromList(list);
                    }

                    if(!cleanName.endsWith('zip') && type == 'zip') {
                      cleanName+='.zip';
                    }
                    await
                    FileSaver.instance.saveFile(
                        cleanName, blob,'.txt',mimeType: MimeType.TEXT);
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
    var url = '0.0.0.0';
    var port = 0;
    context.read<UrlSelector>().state.mapOrNull(loaded: (v) {
      url = v.url;
      port = v.port;
    });
    return Scaffold(
        drawer: const NavDrawer(),
        backgroundColor: Colors.white,
        appBar: AppBar(
          iconTheme: const IconThemeData(color: Colors.white),
          backgroundColor: Colors.black87,
          centerTitle: true,
          title: const Text(
            'Ressources',
            style: TextStyle(fontSize: 22.0, color: Colors.white),
          ),
          actions: [Tooltip(message: 'Disconnect from container', child: IconButton(onPressed: (){
            BlocProvider.of<UrlSelector>(context).add(const UrlEvent.disconnect());
          }, icon: const Icon(
  Icons.link_off_outlined,
),))],

        ),
        body: BlocProvider(
            create: (c) => ResourcesBloc(url: url, port: port),
            child: const ResourcesWidget()));
  }
}
