import 'package:bloc/bloc.dart';
import 'dart:convert';
import 'package:container_explorer/bloc/typesystem_bloc/typesystem_event.dart';
import 'package:container_explorer/bloc/typesystem_bloc/typesystem_state.dart';
import 'package:container_explorer/entities/typesystem.dart';
import 'package:http/http.dart' as http;

class TypesystemBloc extends Bloc<TypesystemEvent,TypesystemState> {
  TypesystemBloc({
    required this.url,
    required this.port,
    }) : super(const TypesystemState.initial()) {
      on<TypesystemEvent>(_onTypesystemEvent);
    }

    final String url;
    final int port;

    Future<void> _onTypesystemEvent(TypesystemEvent ev, Emitter<TypesystemState> emit) async {
      if(ev is TypesystemEventLoadTypesystem) {
        final result = await http.get(Uri.parse('http://'+url+":"+port.toString()+"/rest/typesystem"));
        if(result.statusCode==200) {
          emit(TypesystemStateLoaded(typesystem: Typesystem.fromJson(jsonDecode(result.body))));
        }
        else {
          emit(const TypesystemState.failure());
        }
      }
    }
}
