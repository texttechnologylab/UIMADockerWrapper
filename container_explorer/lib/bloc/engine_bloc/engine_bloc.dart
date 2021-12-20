import 'package:bloc/bloc.dart';
import 'dart:convert';
import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:http/http.dart' as http;
import 'package:container_explorer/bloc/engine_bloc/engine_event.dart';
import 'package:container_explorer/bloc/engine_bloc/engine_state.dart';

class EngineBloc extends Bloc<EngineEvent,EngineState> {
  EngineBloc({
    required this.url,
    required this.port,
    }) : super(const EngineState.initial()) {
      on<EngineEvent>(_onEngineEvent);
    }

    final String url;
    final int port;

    Future<void> _onEngineEvent(EngineEvent ev, Emitter<EngineState> emit) async {
      if(ev is EngineEventLoadEngine) {
        final result = await http.get(Uri.parse('http://'+url+":"+port.toString()+"/rest/engine"));
        if(result.statusCode==200) {
          emit(EngineState.loaded(engine: AnalysisEngine.fromJson(jsonDecode(result.body))));
        }
        else {
          emit(const EngineState.failure());
        }

      }
    }
}
