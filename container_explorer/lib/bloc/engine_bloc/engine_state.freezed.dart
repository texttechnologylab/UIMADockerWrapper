// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'engine_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$EngineStateTearOff {
  const _$EngineStateTearOff();

  EngineStateInitial initial() {
    return const EngineStateInitial();
  }

  EngineStateLoading loading() {
    return const EngineStateLoading();
  }

  EngineStateFailure failure() {
    return const EngineStateFailure();
  }

  EngineStateLoaded loaded({required AnalysisEngine engine}) {
    return EngineStateLoaded(
      engine: engine,
    );
  }
}

/// @nodoc
const $EngineState = _$EngineStateTearOff();

/// @nodoc
mixin _$EngineState {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(AnalysisEngine engine) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineStateInitial value) initial,
    required TResult Function(EngineStateLoading value) loading,
    required TResult Function(EngineStateFailure value) failure,
    required TResult Function(EngineStateLoaded value) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $EngineStateCopyWith<$Res> {
  factory $EngineStateCopyWith(
          EngineState value, $Res Function(EngineState) then) =
      _$EngineStateCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineStateCopyWithImpl<$Res> implements $EngineStateCopyWith<$Res> {
  _$EngineStateCopyWithImpl(this._value, this._then);

  final EngineState _value;
  // ignore: unused_field
  final $Res Function(EngineState) _then;
}

/// @nodoc
abstract class $EngineStateInitialCopyWith<$Res> {
  factory $EngineStateInitialCopyWith(
          EngineStateInitial value, $Res Function(EngineStateInitial) then) =
      _$EngineStateInitialCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineStateInitialCopyWithImpl<$Res>
    extends _$EngineStateCopyWithImpl<$Res>
    implements $EngineStateInitialCopyWith<$Res> {
  _$EngineStateInitialCopyWithImpl(
      EngineStateInitial _value, $Res Function(EngineStateInitial) _then)
      : super(_value, (v) => _then(v as EngineStateInitial));

  @override
  EngineStateInitial get _value => super._value as EngineStateInitial;
}

/// @nodoc

class _$EngineStateInitial implements EngineStateInitial {
  const _$EngineStateInitial();

  @override
  String toString() {
    return 'EngineState.initial()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is EngineStateInitial);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(AnalysisEngine engine) loaded,
  }) {
    return initial();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
  }) {
    return initial?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineStateInitial value) initial,
    required TResult Function(EngineStateLoading value) loading,
    required TResult Function(EngineStateFailure value) failure,
    required TResult Function(EngineStateLoaded value) loaded,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class EngineStateInitial implements EngineState {
  const factory EngineStateInitial() = _$EngineStateInitial;
}

/// @nodoc
abstract class $EngineStateLoadingCopyWith<$Res> {
  factory $EngineStateLoadingCopyWith(
          EngineStateLoading value, $Res Function(EngineStateLoading) then) =
      _$EngineStateLoadingCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineStateLoadingCopyWithImpl<$Res>
    extends _$EngineStateCopyWithImpl<$Res>
    implements $EngineStateLoadingCopyWith<$Res> {
  _$EngineStateLoadingCopyWithImpl(
      EngineStateLoading _value, $Res Function(EngineStateLoading) _then)
      : super(_value, (v) => _then(v as EngineStateLoading));

  @override
  EngineStateLoading get _value => super._value as EngineStateLoading;
}

/// @nodoc

class _$EngineStateLoading implements EngineStateLoading {
  const _$EngineStateLoading();

  @override
  String toString() {
    return 'EngineState.loading()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is EngineStateLoading);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(AnalysisEngine engine) loaded,
  }) {
    return loading();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
  }) {
    return loading?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineStateInitial value) initial,
    required TResult Function(EngineStateLoading value) loading,
    required TResult Function(EngineStateFailure value) failure,
    required TResult Function(EngineStateLoaded value) loaded,
  }) {
    return loading(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
  }) {
    return loading?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading(this);
    }
    return orElse();
  }
}

abstract class EngineStateLoading implements EngineState {
  const factory EngineStateLoading() = _$EngineStateLoading;
}

/// @nodoc
abstract class $EngineStateFailureCopyWith<$Res> {
  factory $EngineStateFailureCopyWith(
          EngineStateFailure value, $Res Function(EngineStateFailure) then) =
      _$EngineStateFailureCopyWithImpl<$Res>;
}

/// @nodoc
class _$EngineStateFailureCopyWithImpl<$Res>
    extends _$EngineStateCopyWithImpl<$Res>
    implements $EngineStateFailureCopyWith<$Res> {
  _$EngineStateFailureCopyWithImpl(
      EngineStateFailure _value, $Res Function(EngineStateFailure) _then)
      : super(_value, (v) => _then(v as EngineStateFailure));

  @override
  EngineStateFailure get _value => super._value as EngineStateFailure;
}

/// @nodoc

class _$EngineStateFailure implements EngineStateFailure {
  const _$EngineStateFailure();

  @override
  String toString() {
    return 'EngineState.failure()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is EngineStateFailure);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(AnalysisEngine engine) loaded,
  }) {
    return failure();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
  }) {
    return failure?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineStateInitial value) initial,
    required TResult Function(EngineStateLoading value) loading,
    required TResult Function(EngineStateFailure value) failure,
    required TResult Function(EngineStateLoaded value) loaded,
  }) {
    return failure(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
  }) {
    return failure?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure(this);
    }
    return orElse();
  }
}

abstract class EngineStateFailure implements EngineState {
  const factory EngineStateFailure() = _$EngineStateFailure;
}

/// @nodoc
abstract class $EngineStateLoadedCopyWith<$Res> {
  factory $EngineStateLoadedCopyWith(
          EngineStateLoaded value, $Res Function(EngineStateLoaded) then) =
      _$EngineStateLoadedCopyWithImpl<$Res>;
  $Res call({AnalysisEngine engine});

  $AnalysisEngineCopyWith<$Res> get engine;
}

/// @nodoc
class _$EngineStateLoadedCopyWithImpl<$Res>
    extends _$EngineStateCopyWithImpl<$Res>
    implements $EngineStateLoadedCopyWith<$Res> {
  _$EngineStateLoadedCopyWithImpl(
      EngineStateLoaded _value, $Res Function(EngineStateLoaded) _then)
      : super(_value, (v) => _then(v as EngineStateLoaded));

  @override
  EngineStateLoaded get _value => super._value as EngineStateLoaded;

  @override
  $Res call({
    Object? engine = freezed,
  }) {
    return _then(EngineStateLoaded(
      engine: engine == freezed
          ? _value.engine
          : engine // ignore: cast_nullable_to_non_nullable
              as AnalysisEngine,
    ));
  }

  @override
  $AnalysisEngineCopyWith<$Res> get engine {
    return $AnalysisEngineCopyWith<$Res>(_value.engine, (value) {
      return _then(_value.copyWith(engine: value));
    });
  }
}

/// @nodoc

class _$EngineStateLoaded implements EngineStateLoaded {
  const _$EngineStateLoaded({required this.engine});

  @override
  final AnalysisEngine engine;

  @override
  String toString() {
    return 'EngineState.loaded(engine: $engine)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is EngineStateLoaded &&
            (identical(other.engine, engine) || other.engine == engine));
  }

  @override
  int get hashCode => Object.hash(runtimeType, engine);

  @JsonKey(ignore: true)
  @override
  $EngineStateLoadedCopyWith<EngineStateLoaded> get copyWith =>
      _$EngineStateLoadedCopyWithImpl<EngineStateLoaded>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(AnalysisEngine engine) loaded,
  }) {
    return loaded(engine);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
  }) {
    return loaded?.call(engine);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(AnalysisEngine engine)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(engine);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(EngineStateInitial value) initial,
    required TResult Function(EngineStateLoading value) loading,
    required TResult Function(EngineStateFailure value) failure,
    required TResult Function(EngineStateLoaded value) loaded,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(EngineStateInitial value)? initial,
    TResult Function(EngineStateLoading value)? loading,
    TResult Function(EngineStateFailure value)? failure,
    TResult Function(EngineStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class EngineStateLoaded implements EngineState {
  const factory EngineStateLoaded({required AnalysisEngine engine}) =
      _$EngineStateLoaded;

  AnalysisEngine get engine;
  @JsonKey(ignore: true)
  $EngineStateLoadedCopyWith<EngineStateLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}
