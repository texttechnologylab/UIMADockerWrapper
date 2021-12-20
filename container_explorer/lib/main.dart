import 'package:container_explorer/presentation/dockerfile_screen.dart';
import 'package:container_explorer/presentation/information_screen.dart';
import 'package:container_explorer/presentation/resources_screen.dart';
import 'package:container_explorer/presentation/typesystem_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import './presentation/analysis_engine_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  static const String routeName = '/analysis_engine';

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Container engine explorer',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        primaryColor: Colors.blue,

        fontFamily: 'Georgia',

        textTheme: const TextTheme(
          headline1: TextStyle(fontSize: 72.0, fontWeight: FontWeight.bold),
          headline6: TextStyle(fontSize: 36.0, fontStyle: FontStyle.italic),
          bodyText2: TextStyle(fontSize: 14.0, fontFamily: 'Hind'),
        ),
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const InformationScreen(),
        '/analysis_engine': (context) => const AnalysisEngineScreen(),
        '/typesystem': (context) => const TypesystemScreen(),
        '/dockerfile': (context) => const DockerfileScreen(),
        '/resources': (context) => const ResourcesScreen(),
      },
    );
  }
}
