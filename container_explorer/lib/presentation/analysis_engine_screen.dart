import 'package:container_explorer/bloc/engine_bloc/engine_bloc.dart';
import 'package:container_explorer/bloc/engine_bloc/engine_event.dart';
import 'package:container_explorer/bloc/engine_bloc/engine_state.dart';
import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:container_explorer/entities/annotator.dart';
import 'package:container_explorer/entities/parameter.dart';
import 'package:container_explorer/presentation/nav_drawer.dart'; import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_fancy_tree_view/flutter_fancy_tree_view.dart';
import 'dart:html' as html;

class TreeNodeTile extends StatefulWidget {
  const TreeNodeTile({Key? key}) : super(key: key);

  @override
  TreeNodeTileState createState() => TreeNodeTileState();
}

List<Widget>? render_sofa_mappings(AnalysisEngine eng) {
  if (eng.sofa_mappings.isNotEmpty) {
    return eng.sofa_mappings
        .map((e) => Row(mainAxisSize: MainAxisSize.min, children: [
              Text('Sofa: '),
              Flexible(
                  child: Text(
                e.component_sofa,
                style: const TextStyle(color: Colors.blue),
              )),
              const Icon(
                Icons.arrow_forward,
                size: 16,
              ),
              Text(e.aggregate_sofa,
                  style: const TextStyle(color: Colors.blue)),
            ]))
        .toList();
  }
  return null;
}

class ValueWidget extends StatefulWidget {
  const ValueWidget({Key? key, required this.param}) : super(key: key);
  final Parameter param;

  @override
  State<ValueWidget> createState() => ValueWidgetState(param: param);
}

class ValueWidgetState extends State<ValueWidget> {
  ValueWidgetState(
      {Key? key,required this.param, this.controllers,this.values}) : super() {
      if (param.value != null) {
        List<String> tempvalues = [];
    if (param.multivalued) {
        if (param.value is List<int>) {
          tempvalues.addAll(
              (param.value as List<int>).map((e) => e.toString()));
        } else if (param.value is List<double>) {
          tempvalues.addAll((param.value as List<double>)
              .map((e) => e.toString()));
        } else if (param.value is List<String>) {
          tempvalues.addAll((param.value as List<String>)
              .map((e) => e.toString()));
        }
      } else {
        if (param.value is int) {
          tempvalues.add((param.value as int).toString());
        } else if (param.value is double) {
          tempvalues.add((param.value as double).toString());
        } else if (param.value is String) {
          tempvalues.add(param.value as String);
        }
      }
      controllers = tempvalues.map((e) => TextEditingController(text: e)).toList();
      values = tempvalues;
      }
    }
  List<TextEditingController>? controllers;
  List<String>? values;
  Parameter param;

  @override
  Widget build(BuildContext context) {
    List<Widget> children = [];
    if(values!=null && controllers!=null) {
      for(int i = 0; i < controllers!.length; i++) {
        children.add(SizedBox(width: 200,
        child: Text(values![i]),
        ));
        children.add(const SizedBox(height: 10));
      }
    }
    else {
      children.add(const Text('null',style: TextStyle(color: Colors.orange)));
    }
    List<Widget> lastRow = [];
    children.add(Row(children: lastRow));
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: children);
  }
}

class ValueSwitchWidget extends StatelessWidget {
  const ValueSwitchWidget({Key? key, required this.annotator})
      : super(key: key);

  final Parameter annotator;

  @override
  Widget build(BuildContext context) {
    if (annotator.type != 'Boolean') {
      return ValueWidget(param: annotator);
    } else {
      if(annotator.value!=null) {
        return Text(annotator.value==true?'true':'false');
      }
      else {
        return const Text('null');
      }
    }
  }
}

class AnnotatorWidget extends StatelessWidget {
  const AnnotatorWidget({Key? key, required this.annotator}) : super(key: key);

  final Annotator annotator;

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: const EdgeInsets.only(left: 40),
        child: Column(
            children: annotator.parameters.map((e) {
          return ExpansionTile(
              iconColor: Colors.green,
              leading: const Icon(Icons.functions),
              title: Text(e.name),
              children: [
                Row(children: [
                  const SizedBox(width: 30),
                  const Text('Mandatory:  ',
                      style: TextStyle(fontWeight: FontWeight.bold)),
                  Text(e.mandatory.toString())
                ]),
                const SizedBox(height: 10),
                Row(children: [
                  const SizedBox(width: 30),
                  const Text('Multivalued: ',
                      style: TextStyle(fontWeight: FontWeight.bold)),
                  Text(e.multivalued.toString())
                ]),
                const SizedBox(height: 10),
                Row(children: [
                  const SizedBox(width: 30),
                  const Text('Description:  ',
                      style: TextStyle(fontWeight: FontWeight.bold)),
                  Flexible(child: Text(e.description))
                ]),
                const SizedBox(height: 10),
                Row(children: [
                  const SizedBox(width: 30),
                  const Text('Type:              ',
                      style: TextStyle(fontWeight: FontWeight.bold)),
                  Text(e.type),
                ]),
                const SizedBox(height: 10),
                Row(crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.start,children: [
                  const SizedBox(width: 30),
                  const Text('Value:            ',
                      style: TextStyle(fontWeight: FontWeight.bold)),
                  ValueSwitchWidget(annotator: e),
                ]),
                const SizedBox(height: 10),
              ]);
        }).toList()));
  }
}

class TreeNodeTileState extends State<TreeNodeTile> {
  Widget renderEngine(BuildContext context) {
    final nodeScope = TreeNodeScope.of(context);
    var children = <Widget>[];
    AnalysisEngine data = nodeScope.node.data as AnalysisEngine;
    var result = render_sofa_mappings(data);

    Widget subtitle;
    if (data.engines != null) {
      subtitle =
          Text("Components: " + nodeScope.node.children.length.toString());
    } else {
      subtitle =
          Text("Parameters: " + data.annotator!.parameters.length.toString());
      children.add(AnnotatorWidget(annotator: data.annotator!));
    }

    if (result != null) {
      subtitle = Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [subtitle, ...result]);
    }

    Color col = (nodeScope.isExpanded) ? Colors.green : Colors.grey;

    return InkWell(
      child: Row(mainAxisSize: MainAxisSize.min, children: [
        const LinesWidget(),
        const SizedBox(width: 4.0),
        Expanded(
            child: ExpansionTile(
                leading: (data.engines == null)
                    ? Icon(Icons.memory, color: col)
                    : Icon(Icons.dynamic_feed, color: col),
                onExpansionChanged: (scope) {
                  nodeScope.toggleExpanded(context);
                },
                title: Text(nodeScope.node.label),
                subtitle: subtitle,
                children: children))
      ]),
    );
  }

  @override
  Widget build(BuildContext context) {
    return renderEngine(context);
  }
}

class AnalysisEngineWidget extends StatelessWidget {
  const AnalysisEngineWidget({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<EngineBloc, EngineState>(builder: (ctx, state) {
      return state.map(initial: (a) {
        ctx.read<EngineBloc>().add(const EngineEvent.loadEngine());
        return const Text('Initial state');
      }, loading: (a) {
        return const Center(child: CircularProgressIndicator());
      }, failure: (a) {
        return const Text(
          'There was an error!',
          style: TextStyle(color: Colors.red),
        );
      }, loaded: (e) {
        if (e.engine.engines != null) {
          return renderaggregate(e.engine, 0);
        } else {
          return Text('Primitive engine name: ' + e.engine.annotator!.name);
        }
      });
    });
  }

  Widget renderaggregate(AnalysisEngine engine, int level) {
    TreeNode root = TreeNode(id: "root");
    root.addChild(addchildren(engine, "root", 0));

    return TreeView(
      theme: const TreeViewTheme(lineStyle: LineStyle.disabled, indent: 40),
      nodeBuilder: (context, node) {
        return const TreeNodeTile();
      },
      controller: TreeViewController(rootNode: root),
    );
  }

  TreeNode addchildren(AnalysisEngine engine, String parentkey, int index) {
    if (engine.engines != null) {
      TreeNode node = TreeNode(
          id: parentkey.toString() + "." + index.toString(),
          label: 'Aggregate analysis engine',
          data: engine);
      for (int i = 0; i < engine.engines!.length; i++) {
        node.addChild(addchildren(engine.engines![i], node.id, i));
      }
      return node;
    } else {
      TreeNode node = TreeNode(
          id: parentkey.toString() + "." + index.toString(),
          label: engine.annotator!.name.split(".").last,
          data: engine);
      return node;
    }
  }
}

class AnalysisEngineScreen extends StatelessWidget {
  const AnalysisEngineScreen({Key? key}) : super(key: key);

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
            create: (c) => EngineBloc(url: html.window.location.hostname ?? '127.0.0.1', port: 9714),
            child: const AnalysisEngineWidget()));
  }
}
