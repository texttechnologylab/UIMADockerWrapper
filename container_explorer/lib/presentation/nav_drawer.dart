import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class NavDrawer extends StatelessWidget {
  const NavDrawer({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: const EdgeInsets.all(8),
        children: <Widget>[
          DrawerHeader(
            child: SvgPicture.asset('assets/goethe.svg')),
          const Divider(height: 3,color: Colors.grey,),
          ListTile(
            leading: const Icon(Icons.info),
            title: const Text('Information'),
            onTap: ()  { 
              Navigator.pushReplacementNamed(context, '/');
            },
          ),
          ListTile(
            leading: const Icon(Icons.memory),
            title: const Text('Analysis engine'),
            onTap: ()  { 
              Navigator.pushReplacementNamed(context, '/analysis_engine');
            },
          ),
          ListTile(
            leading: const Icon(Icons.code),
            title: const Text('Typesystem'),
            onTap: () {
              Navigator.pushReplacementNamed(context, '/typesystem');
            },
          ),
          ListTile(
            leading: const Icon(Icons.folder),
            title: const Text('Dockerfile'),
            onTap: () {
              Navigator.pushReplacementNamed(context, '/dockerfile');
            }
          ),
          ListTile(
            leading: const Icon(Icons.file_copy),
            title: const Text('Other ressources'),
            onTap: () {
              Navigator.pushReplacementNamed(context, '/resources');
            },
          ),
          ],
      ),
    );
  }
}

