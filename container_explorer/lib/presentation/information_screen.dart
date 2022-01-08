import 'package:container_explorer/bloc/url_event.dart';
import 'package:container_explorer/bloc/url_selector.dart';
import 'package:container_explorer/presentation/endpoint_widget.dart';
import 'package:container_explorer/presentation/nav_drawer.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_linkify/flutter_linkify.dart';
import 'package:url_launcher/url_launcher.dart';

class InformationHeader extends StatelessWidget {
  const InformationHeader({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Column(children: [
      const SizedBox(height: 20,),
      const Text(
        'UIMADockerWrapper',
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
      const SizedBox(height: 10),
      Container(margin:EdgeInsets.all(16),child: Linkify(onOpen: (a) async {await launch(a.url);},text: 'This page shall provide a short summary of the container and the wrapped components. The project is still in the alpha version, please report any bugs and problems to https://github.com/texttechnologylab/ReproducibleUIMAAnnotations')),
    ]);
  }
}

class InformationScreen extends StatelessWidget {
  const InformationScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: const NavDrawer(),
        backgroundColor: Colors.white,
        appBar: AppBar(
          iconTheme: IconThemeData(color: Colors.white),
          backgroundColor: Colors.black87,
          centerTitle: true,
          title: const Text(
            'Information',
            style: TextStyle(fontSize: 22.0, color: Colors.white),
          ),
          actions: [Tooltip(message: 'Disconnect from container', child: IconButton(onPressed: (){
            BlocProvider.of<UrlSelector>(context).add(const UrlEvent.disconnect());
          }, icon: const Icon(
  Icons.link_off_outlined,
),))],
        ),
        body: SingleChildScrollView(scrollDirection: Axis.vertical,child: Column(children: [
          const InformationHeader(),
          const SizedBox(height: 30),
          const Text(
            'Endpoints',
            textAlign: TextAlign.left,
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
          ),
          EndpointWidget(type: "GET", url: "/rest/typesystem", children: const [
            Text("Returns the used typesystem in the JSON format")
          ]),
          EndpointWidget(type: 'POST', url: "/process", children: const [
            Text(
                'The body must contain a valid CAS representation in the XML format')
          ]),
          EndpointWidget(type: 'POST', url: "/set_typesystem", children: const [
            Text(
                'The body must contain a valid UIMA typesystem representation in the XML format')
          ]),
          EndpointWidget(type: 'GET', url: "/rest/engine", children: const [
            Text(
                'Returns the uima analysis engine representation in the JSON format')
          ]),
          EndpointWidget(type: 'GET', url: "/rest/dockerfile", children: const [
            Text('Returns the dockerfile representation as plain text.')
          ]),
          EndpointWidget(type: 'GET', url: "/rest/resources", children: const [
            Text(
                'Returns the dockerfile representation a JSON representation of the used resources which were accessible at container creation time.')
          ])
        ])));
  }
}
