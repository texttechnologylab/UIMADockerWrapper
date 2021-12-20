import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:container_explorer/entities/typesystem.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'typesystem_state.freezed.dart';


@freezed
class TypesystemState with _$TypesystemState {
  const factory TypesystemState.initial() = TypesystemStateInitial;
  const factory TypesystemState.loading() = TypesystemStateLoading;
  const factory TypesystemState.failure() = TypesystemStateFailure;
  const factory TypesystemState.loaded({
    required Typesystem typesystem, 
  }) = TypesystemStateLoaded;
}
