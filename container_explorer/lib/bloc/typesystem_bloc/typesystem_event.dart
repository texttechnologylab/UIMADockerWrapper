import 'package:freezed_annotation/freezed_annotation.dart';
part 'typesystem_event.freezed.dart';


@freezed
class TypesystemEvent with _$TypesystemEvent {
  const factory TypesystemEvent.loadTypesystem() = TypesystemEventLoadTypesystem;
}
