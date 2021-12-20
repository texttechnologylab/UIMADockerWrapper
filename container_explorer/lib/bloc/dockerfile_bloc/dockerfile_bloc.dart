import 'package:bloc/bloc.dart';
import 'package:container_explorer/bloc/dockerfile_bloc/dockerfile_event.dart';
import 'package:container_explorer/bloc/dockerfile_bloc/dockerfile_state.dart';
import 'package:http/http.dart' as http;

class DockerfileBloc extends Bloc<DockerfileEvent,DockerfileState> {
  DockerfileBloc({
    required this.url,
    required this.port,
    }) : super(const DockerfileState.initial()) {
      on<DockerfileEvent>(_onTypesystemEvent);
    }

    final String url;
    final int port;

    Future<void> _onTypesystemEvent(DockerfileEvent ev, Emitter<DockerfileState> emit) async {
      if(ev is DockerfileEventLoad) {
        final result = await http.get(Uri.parse('http://'+url+":"+port.toString()+"/rest/dockerfile"));
        if(result.statusCode==200) {
          emit(DockerfileState.loaded(dockerfile: result.body));
        }
        else {
          emit(const DockerfileState.failure());
        }
      }
    }
}
