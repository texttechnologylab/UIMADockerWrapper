import 'package:freezed_annotation/freezed_annotation.dart';
part 'resources_event.freezed.dart';


@freezed
class ResourcesEvent with _$ResourcesEvent {
  const factory ResourcesEvent.loadTypesystem() = ResourcesEventLoadEvent;
}
