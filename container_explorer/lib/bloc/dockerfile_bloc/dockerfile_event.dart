import 'package:freezed_annotation/freezed_annotation.dart';
part 'dockerfile_event.freezed.dart';


@freezed
class DockerfileEvent with _$DockerfileEvent {
  const factory DockerfileEvent.loadDockerfile() = DockerfileEventLoad;
}
