import 'package:container_explorer/entities/typesystem.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'resources_state.freezed.dart';


@freezed
class ResourcesState with _$ResourcesState {
  const factory ResourcesState.initial() = ResourcesStateInitial;
  const factory ResourcesState.loading() = ResourcesStateLoading;
  const factory ResourcesState.failure() = ResourcesStateFailure;
  const factory ResourcesState.loaded({
    required Map<String,dynamic> res,
  }) = ResourcesStateLoaded;
}
