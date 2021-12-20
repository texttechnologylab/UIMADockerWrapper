import 'package:container_explorer/bloc/typesystem_bloc/typesystem_bloc.dart';
import 'package:container_explorer/bloc/typesystem_bloc/typesystem_event.dart';
import 'package:container_explorer/bloc/typesystem_bloc/typesystem_state.dart';
import 'package:container_explorer/entities/type.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_fancy_tree_view/flutter_fancy_tree_view.dart';
import 'dart:html' as html;

import './nav_drawer.dart';

class TypesystemTileState extends StatelessWidget {
  const TypesystemTileState({Key? key}) : super(key: key);
  Widget renderEngine(BuildContext context) {
    final nodeScope = TreeNodeScope.of(context);
    Color col = (nodeScope.isExpanded) ? Colors.green : Colors.grey;

    return InkWell(
        onTap: () {
          nodeScope.toggleExpanded(context);
        },
        child: Row(children: [
          const LinesWidget(),
          const SizedBox(width: 4.0),
          (nodeScope.node.children.isEmpty)
              ? Icon(Icons.memory, color: col)
              : Icon(Icons.dynamic_feed, color: col),
          const SizedBox(width: 6.0),
          Expanded(child: Text(nodeScope.node.label))
        ]));
  }

  @override
  Widget build(BuildContext context) {
    return renderEngine(context);
  }
}

class TypesystemWidget extends StatelessWidget {
  const TypesystemWidget({Key? key}) : super(key: key);

  void addAllChildren(TreeNode root, List<UIMAType> children) {
    for (UIMAType t in children) {
      TreeNode node = TreeNode(id: t.name, label: t.name);
      addAllChildren(node, t.children);
      root.addChild(node);
    }
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<TypesystemBloc, TypesystemState>(builder: (ctx, state) {
      return state.map(initial: (a) {
        ctx.read<TypesystemBloc>().add(const TypesystemEvent.loadTypesystem());
        return const Text('Initial state');
      }, loading: (a) {
        return const Center(child: CircularProgressIndicator());
      }, failure: (a) {
        return const Text(
          'There was an error!',
          style: TextStyle(color: Colors.red),
        );
      }, loaded: (e) {
        TreeNode root = TreeNode(id: "root");
        addAllChildren(root, e.typesystem.typesystem);

        return TreeView(
            theme: const TreeViewTheme(indent: 30, lineStyle: LineStyle.disabled),
            nodeBuilder: (context, node) {
              return const TypesystemTileState();
            },
            controller: TreeViewController(rootNode: root));
      });
    });
  }
}

class TypesystemScreen extends StatelessWidget {
  const TypesystemScreen({Key? key}) : super(key: key);
  static const String routeName = '/typesystem';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: const NavDrawer(),
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Colors.black,
          centerTitle: true,
          title: const Text(
            'Reproducible UIMA Annotations',
            style: TextStyle(fontWeight: FontWeight.bold, color: Colors.white),
          ),
        ),
        body: BlocProvider(
            create: (c) => TypesystemBloc(url: html.window.location.hostname ?? '127.0.0.1', port: 9714),
            child: const TypesystemWidget()));
  }
}
