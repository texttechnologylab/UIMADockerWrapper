// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'engine_event.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$EngineEventTearOff {
  const _$EngineEventTearOff();

  EngineEventLoadEngine loadEngine() {
    return const EngineEventLoadEngine();
  }
}

/// @nodoc
const $EngineEvent = _$EngineEventTearOff();

/// @nodoc
mixin _$EngineEvent {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadEngine,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadEngine,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadEngine,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineEventLoadEngine value) loadEngine,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineEventLoadEngine value)? loadEngine,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineEventLoadEngine value)? loadEngine,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $EngineEventCopyWith<$Res> {
  factory $EngineEventCopyWith(
          EngineEvent value, $Res Function(EngineEvent) then) =
      _$EngineEventCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineEventCopyWithImpl<$Res> implements $EngineEventCopyWith<$Res> {
  _$EngineEventCopyWithImpl(this._value, this._then);

  final EngineEvent _value;
  // ignore: unused_field
  final $Res Function(EngineEvent) _then;
}

/// @nodoc
abstract class $EngineEventLoadEngineCopyWith<$Res> {
  factory $EngineEventLoadEngineCopyWith(EngineEventLoadEngine value,
          $Res Function(EngineEventLoadEngine) then) =
      _$EngineEventLoadEngineCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineEventLoadEngineCopyWithImpl<$Res>
    extends _$EngineEventCopyWithImpl<$Res>
    implements $EngineEventLoadEngineCopyWith<$Res> {
  _$EngineEventLoadEngineCopyWithImpl(
      EngineEventLoadEngine _value, $Res Function(EngineEventLoadEngine) _then)
      : super(_value, (v) => _then(v as EngineEventLoadEngine));

  @override
  EngineEventLoadEngine get _value => super._value as EngineEventLoadEngine;
}

/// @nodoc

class _$EngineEventLoadEngine implements EngineEventLoadEngine {
  const _$EngineEventLoadEngine();

  @override
  String toString() {
    return 'EngineEvent.loadEngine()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is EngineEventLoadEngine);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadEngine,
  }) {
    return loadEngine();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadEngine,
  }) {
    return loadEngine?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadEngine,
    required TResult orElse(),
  }) {
    if (loadEngine != null) {
      return loadEngine();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineEventLoadEngine value) loadEngine,
  }) {
    return loadEngine(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineEventLoadEngine value)? loadEngine,
  }) {
    return loadEngine?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineEventLoadEngine value)? loadEngine,
    required TResult orElse(),
  }) {
    if (loadEngine != null) {
      return loadEngine(this);
    }
    return orElse();
  }
}

abstract class EngineEventLoadEngine implements EngineEvent {
  const factory EngineEventLoadEngine() = _$EngineEventLoadEngine;
}
