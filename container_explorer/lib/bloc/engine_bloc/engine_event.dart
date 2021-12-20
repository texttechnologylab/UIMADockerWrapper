import 'package:freezed_annotation/freezed_annotation.dart';
part 'engine_event.freezed.dart';


@freezed
class EngineEvent with _$EngineEvent {
  const factory EngineEvent.loadEngine() = EngineEventLoadEngine;
}
