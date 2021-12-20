import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'dockerfile_state.freezed.dart';


@freezed
class DockerfileState with _$DockerfileState {
  const factory DockerfileState.initial() = DockerfileStateInitial;
  const factory DockerfileState.loading() = DockerfileStateLoading;
  const factory DockerfileState.failure() = DockerfileStateFailure;
  const factory DockerfileState.loaded({
    required String dockerfile, 
  }) = DockerfileStateLoaded;
}
