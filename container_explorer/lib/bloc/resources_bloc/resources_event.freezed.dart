// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'resources_event.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$ResourcesEventTearOff {
  const _$ResourcesEventTearOff();

  ResourcesEventLoadEvent loadTypesystem() {
    return const ResourcesEventLoadEvent();
  }
}

/// @nodoc
const $ResourcesEvent = _$ResourcesEventTearOff();

/// @nodoc
mixin _$ResourcesEvent {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadTypesystem,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadTypesystem,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadTypesystem,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(ResourcesEventLoadEvent value) loadTypesystem,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesEventLoadEvent value)? loadTypesystem,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesEventLoadEvent value)? loadTypesystem,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ResourcesEventCopyWith<$Res> {
  factory $ResourcesEventCopyWith(
          ResourcesEvent value, $Res Function(ResourcesEvent) then) =
      _$ResourcesEventCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesEventCopyWithImpl<$Res>
    implements $ResourcesEventCopyWith<$Res> {
  _$ResourcesEventCopyWithImpl(this._value, this._then);

  final ResourcesEvent _value;
  // ignore: unused_field
  final $Res Function(ResourcesEvent) _then;
}

/// @nodoc
abstract class $ResourcesEventLoadEventCopyWith<$Res> {
  factory $ResourcesEventLoadEventCopyWith(ResourcesEventLoadEvent value,
          $Res Function(ResourcesEventLoadEvent) then) =
      _$ResourcesEventLoadEventCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesEventLoadEventCopyWithImpl<$Res>
    extends _$ResourcesEventCopyWithImpl<$Res>
    implements $ResourcesEventLoadEventCopyWith<$Res> {
  _$ResourcesEventLoadEventCopyWithImpl(ResourcesEventLoadEvent _value,
      $Res Function(ResourcesEventLoadEvent) _then)
      : super(_value, (v) => _then(v as ResourcesEventLoadEvent));

  @override
  ResourcesEventLoadEvent get _value => super._value as ResourcesEventLoadEvent;
}

/// @nodoc

class _$ResourcesEventLoadEvent implements ResourcesEventLoadEvent {
  const _$ResourcesEventLoadEvent();

  @override
  String toString() {
    return 'ResourcesEvent.loadTypesystem()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is ResourcesEventLoadEvent);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadTypesystem,
  }) {
    return loadTypesystem();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadTypesystem,
  }) {
    return loadTypesystem?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadTypesystem,
    required TResult orElse(),
  }) {
    if (loadTypesystem != null) {
      return loadTypesystem();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(ResourcesEventLoadEvent value) loadTypesystem,
  }) {
    return loadTypesystem(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesEventLoadEvent value)? loadTypesystem,
  }) {
    return loadTypesystem?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesEventLoadEvent value)? loadTypesystem,
    required TResult orElse(),
  }) {
    if (loadTypesystem != null) {
      return loadTypesystem(this);
    }
    return orElse();
  }
}

abstract class ResourcesEventLoadEvent implements ResourcesEvent {
  const factory ResourcesEventLoadEvent() = _$ResourcesEventLoadEvent;
}
