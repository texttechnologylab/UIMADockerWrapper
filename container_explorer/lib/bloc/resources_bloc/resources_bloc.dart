import 'package:bloc/bloc.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_event.dart';
import 'package:container_explorer/bloc/resources_bloc/resources_state.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class ResourcesBloc extends Bloc<ResourcesEvent,ResourcesState> {
  ResourcesBloc({
    required this.url,
    required this.port,
    }) : super(const ResourcesState.initial()) {
      on<ResourcesEvent>(_onTypesystemEvent);
    }

    final String url;
    final int port;

    Future<void> _onTypesystemEvent(ResourcesEvent ev, Emitter<ResourcesState> emit) async {
      if(ev is ResourcesEventLoadEvent) {
        final result = await http.get(Uri.parse('http://'+url+":"+port.toString()+"/rest/resources"));
        if(result.statusCode==200) {
          emit(ResourcesState.loaded(res: jsonDecode(result.body)));
        }
        else {
          emit(const ResourcesState.failure());
        }
      }
    }
}
