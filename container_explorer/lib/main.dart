import 'package:container_explorer/bloc/url_selector.dart';
import 'package:container_explorer/bloc/url_state.dart';
import 'package:container_explorer/presentation/connect_screen.dart';
import 'package:container_explorer/presentation/dockerfile_screen.dart';
import 'package:container_explorer/presentation/information_screen.dart';
import 'package:container_explorer/presentation/resources_screen.dart';
import 'package:container_explorer/presentation/typesystem_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
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
          headline1: TextStyle(fontSize: 45.0, fontWeight: FontWeight.bold),
          headline6: TextStyle(fontSize: 36.0, fontStyle: FontStyle.italic),
          bodyText2: TextStyle(fontSize: 14.0, fontFamily: 'Hind'),
        ),
      ),
      builder: (context, widget) => BlocProvider<UrlSelector>(
        create: (_) => UrlSelector(),
        child: widget,
      ),
      home: BlocBuilder<UrlSelector,UrlState>(builder: (ctx,state) {
        return state.map(initial: (v) => MyStatefulWidget(), 
        error: (v) => MyStatefulWidget(),
        loaded: (l) {
          if (l.route == '/') {
            return const InformationScreen();
          }
          else if(l.route == '/analysis_engine') {
            return const AnalysisEngineScreen();
          }
          else if(l.route == '/typesystem') {
            return const TypesystemScreen();
          }
          else if(l.route == '/dockerfile') {
            return const DockerfileScreen();
          }
          else {
            return const ResourcesScreen();
          }
        });
      })
    );
  }
}
