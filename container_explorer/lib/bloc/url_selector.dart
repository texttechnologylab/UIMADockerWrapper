import 'dart:convert';

import 'package:bloc/bloc.dart';
import 'package:container_explorer/bloc/url_event.dart';
import 'package:container_explorer/bloc/url_state.dart';
import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:http/http.dart' as http;

class UrlSelector extends Bloc<UrlEvent,UrlState> {
  UrlSelector() : super(const UrlState.initial()) {
      on<UrlEvent>(_onEngineEvent);
    }


    Future<void> _onEngineEvent(UrlEvent ev, Emitter<UrlState> emit) async {
      if(ev is UrlEventLoaded) {
        try {
          final result = await http.get(Uri.parse('http://'+ev.url+":"+ev.port.toString()+"/rest/engine"));
          if(result.statusCode==200) {
            var eng = AnalysisEngine.fromJson(jsonDecode(result.body));
            emit(UrlState.loaded(url: ev.url, port: ev.port, route: '/'));
          }
          else {
            emit(UrlState.error(url: ev.url, port: ev.port, error: "HTTP return status not 200"));
          }
        }
        catch (e) {
          emit(UrlState.error(url: ev.url, port: ev.port, error: e.toString()));
        }
      }
      else if(ev is UrlEventSetRoute) {
          state.mapOrNull(loaded: (l) {
            emit(UrlState.loaded(url: l.url, port: l.port, route: ev.route));
          });
      }
      else if(ev is UrlEventDisconnect) {
        emit(const UrlState.initial());
      }
    }
}
