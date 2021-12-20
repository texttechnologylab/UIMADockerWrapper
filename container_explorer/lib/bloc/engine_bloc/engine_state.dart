import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'engine_state.freezed.dart';


@freezed
class EngineState with _$EngineState {
  const factory EngineState.initial() = EngineStateInitial;
  const factory EngineState.loading() = EngineStateLoading;
  const factory EngineState.failure() = EngineStateFailure;
  const factory EngineState.loaded({
    required AnalysisEngine engine
  }) = EngineStateLoaded;
}
