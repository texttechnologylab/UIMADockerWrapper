import 'package:container_explorer/bloc/url_event.dart';
import 'package:container_explorer/bloc/url_selector.dart';
import 'package:container_explorer/bloc/url_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class NavDrawer extends StatelessWidget {
  const NavDrawer({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        children: <Widget>[
          Container(child: DrawerHeader(
            child:  Image(image: AssetImage('assets/ttlab.png'))), color: Color(0xFF0069c0)),
            SizedBox(height: 10),
          const Text(" UIMADockerWrapper", style: TextStyle(fontSize: 25)),
          SizedBox(height: 18),
          ListTile(
            leading: Icon(Icons.info),
            title: const Text('Information'),
            onTap: ()  { 
              BlocProvider.of<UrlSelector>(context).add(const UrlEvent.setRoute('/'));
            },
          ),
          ListTile(
            leading: const Icon(Icons.memory),
            title: const Text('Analysis engine'),
            onTap: ()  { 
              BlocProvider.of<UrlSelector>(context).add(const UrlEvent.setRoute('/analysis_engine'));
            },
          ),
          ListTile(
            leading: const Icon(Icons.code),
            title: const Text('Typesystem'),
            onTap: () {
              BlocProvider.of<UrlSelector>(context).add(const UrlEvent.setRoute('/typesystem'));
            },
          ),
          ListTile(
            leading: const Icon(Icons.folder),
            title: const Text('Dockerfile'),
            onTap: () {
              BlocProvider.of<UrlSelector>(context).add(const UrlEvent.setRoute('/dockerfile'));
            }
          ),
          ListTile(
            leading: const Icon(Icons.file_copy),
            title: const Text('Other ressources'),
            onTap: () {
              BlocProvider.of<UrlSelector>(context).add(const UrlEvent.setRoute('/resources'));
            },
          ),
          ],
      ),
    );
  }
}

