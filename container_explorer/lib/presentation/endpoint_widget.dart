import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class EndpointWidget extends StatelessWidget {
  EndpointWidget(
      {Key? key, required this.type, required this.url, required this.children})
      : super(key: key);
  final String type;
  final String url;
  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    Color c = Colors.blue;
    List<Widget> widgets = List.of(children)..insert(0, const Divider(color: Colors.grey,height: 3,));
    if (type == "POST") {
      c = Colors.red;
    }
    Widget wrapper = Container(padding: EdgeInsets.all(6),
    child: Column(children: widgets));

    return Container(
        margin: const EdgeInsets.only(left: 16, right: 16, top: 8, bottom: 8),
        decoration: BoxDecoration(
            border: Border.all(width: 1, color: c),
            borderRadius: const BorderRadius.all(Radius.circular(4))),
        child: ListTileTheme(
            contentPadding: const EdgeInsets.symmetric(horizontal: 6),
            minVerticalPadding: 0,
            dense: true,
            child: ExpansionTile(
                leading: Container(
                    padding: const EdgeInsets.all(6),
                    child: Text(type,
                        style: const TextStyle(
                            color: Colors.white, fontWeight: FontWeight.bold, fontSize: 12)),
                    decoration: BoxDecoration(
                        borderRadius:
                            const BorderRadius.all(Radius.circular(4)),
                        color: c,
                        border: Border.all(width: 1, color: c))),
                title: Text(
                  url,
                  style: const TextStyle(
                      fontSize: 17, fontWeight: FontWeight.bold),
                ),
                children: [wrapper])));
  }
}
