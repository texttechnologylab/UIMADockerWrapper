// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'typesystem_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$TypesystemStateTearOff {
  const _$TypesystemStateTearOff();

  TypesystemStateInitial initial() {
    return const TypesystemStateInitial();
  }

  TypesystemStateLoading loading() {
    return const TypesystemStateLoading();
  }

  TypesystemStateFailure failure() {
    return const TypesystemStateFailure();
  }

  TypesystemStateLoaded loaded({required Typesystem typesystem}) {
    return TypesystemStateLoaded(
      typesystem: typesystem,
    );
  }
}

/// @nodoc
const $TypesystemState = _$TypesystemStateTearOff();

/// @nodoc
mixin _$TypesystemState {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Typesystem typesystem) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(TypesystemStateInitial value) initial,
    required TResult Function(TypesystemStateLoading value) loading,
    required TResult Function(TypesystemStateFailure value) failure,
    required TResult Function(TypesystemStateLoaded value) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $TypesystemStateCopyWith<$Res> {
  factory $TypesystemStateCopyWith(
          TypesystemState value, $Res Function(TypesystemState) then) =
      _$TypesystemStateCopyWithImpl<$Res>;
}

/// @nodoc
class _$TypesystemStateCopyWithImpl<$Res>
    implements $TypesystemStateCopyWith<$Res> {
  _$TypesystemStateCopyWithImpl(this._value, this._then);

  final TypesystemState _value;
  // ignore: unused_field
  final $Res Function(TypesystemState) _then;
}

/// @nodoc
abstract class $TypesystemStateInitialCopyWith<$Res> {
  factory $TypesystemStateInitialCopyWith(TypesystemStateInitial value,
          $Res Function(TypesystemStateInitial) then) =
      _$TypesystemStateInitialCopyWithImpl<$Res>;
}

/// @nodoc
class _$TypesystemStateInitialCopyWithImpl<$Res>
    extends _$TypesystemStateCopyWithImpl<$Res>
    implements $TypesystemStateInitialCopyWith<$Res> {
  _$TypesystemStateInitialCopyWithImpl(TypesystemStateInitial _value,
      $Res Function(TypesystemStateInitial) _then)
      : super(_value, (v) => _then(v as TypesystemStateInitial));

  @override
  TypesystemStateInitial get _value => super._value as TypesystemStateInitial;
}

/// @nodoc

class _$TypesystemStateInitial implements TypesystemStateInitial {
  const _$TypesystemStateInitial();

  @override
  String toString() {
    return 'TypesystemState.initial()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is TypesystemStateInitial);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Typesystem typesystem) loaded,
  }) {
    return initial();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
  }) {
    return initial?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
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
    required TResult Function(TypesystemStateInitial value) initial,
    required TResult Function(TypesystemStateLoading value) loading,
    required TResult Function(TypesystemStateFailure value) failure,
    required TResult Function(TypesystemStateLoaded value) loaded,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class TypesystemStateInitial implements TypesystemState {
  const factory TypesystemStateInitial() = _$TypesystemStateInitial;
}

/// @nodoc
abstract class $TypesystemStateLoadingCopyWith<$Res> {
  factory $TypesystemStateLoadingCopyWith(TypesystemStateLoading value,
          $Res Function(TypesystemStateLoading) then) =
      _$TypesystemStateLoadingCopyWithImpl<$Res>;
}

/// @nodoc
class _$TypesystemStateLoadingCopyWithImpl<$Res>
    extends _$TypesystemStateCopyWithImpl<$Res>
    implements $TypesystemStateLoadingCopyWith<$Res> {
  _$TypesystemStateLoadingCopyWithImpl(TypesystemStateLoading _value,
      $Res Function(TypesystemStateLoading) _then)
      : super(_value, (v) => _then(v as TypesystemStateLoading));

  @override
  TypesystemStateLoading get _value => super._value as TypesystemStateLoading;
}

/// @nodoc

class _$TypesystemStateLoading implements TypesystemStateLoading {
  const _$TypesystemStateLoading();

  @override
  String toString() {
    return 'TypesystemState.loading()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is TypesystemStateLoading);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Typesystem typesystem) loaded,
  }) {
    return loading();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
  }) {
    return loading?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
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
    required TResult Function(TypesystemStateInitial value) initial,
    required TResult Function(TypesystemStateLoading value) loading,
    required TResult Function(TypesystemStateFailure value) failure,
    required TResult Function(TypesystemStateLoaded value) loaded,
  }) {
    return loading(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
  }) {
    return loading?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading(this);
    }
    return orElse();
  }
}

abstract class TypesystemStateLoading implements TypesystemState {
  const factory TypesystemStateLoading() = _$TypesystemStateLoading;
}

/// @nodoc
abstract class $TypesystemStateFailureCopyWith<$Res> {
  factory $TypesystemStateFailureCopyWith(TypesystemStateFailure value,
          $Res Function(TypesystemStateFailure) then) =
      _$TypesystemStateFailureCopyWithImpl<$Res>;
}

/// @nodoc
class _$TypesystemStateFailureCopyWithImpl<$Res>
    extends _$TypesystemStateCopyWithImpl<$Res>
    implements $TypesystemStateFailureCopyWith<$Res> {
  _$TypesystemStateFailureCopyWithImpl(TypesystemStateFailure _value,
      $Res Function(TypesystemStateFailure) _then)
      : super(_value, (v) => _then(v as TypesystemStateFailure));

  @override
  TypesystemStateFailure get _value => super._value as TypesystemStateFailure;
}

/// @nodoc

class _$TypesystemStateFailure implements TypesystemStateFailure {
  const _$TypesystemStateFailure();

  @override
  String toString() {
    return 'TypesystemState.failure()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is TypesystemStateFailure);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Typesystem typesystem) loaded,
  }) {
    return failure();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
  }) {
    return failure?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
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
    required TResult Function(TypesystemStateInitial value) initial,
    required TResult Function(TypesystemStateLoading value) loading,
    required TResult Function(TypesystemStateFailure value) failure,
    required TResult Function(TypesystemStateLoaded value) loaded,
  }) {
    return failure(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
  }) {
    return failure?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure(this);
    }
    return orElse();
  }
}

abstract class TypesystemStateFailure implements TypesystemState {
  const factory TypesystemStateFailure() = _$TypesystemStateFailure;
}

/// @nodoc
abstract class $TypesystemStateLoadedCopyWith<$Res> {
  factory $TypesystemStateLoadedCopyWith(TypesystemStateLoaded value,
          $Res Function(TypesystemStateLoaded) then) =
      _$TypesystemStateLoadedCopyWithImpl<$Res>;
  $Res call({Typesystem typesystem});

  $TypesystemCopyWith<$Res> get typesystem;
}

/// @nodoc
class _$TypesystemStateLoadedCopyWithImpl<$Res>
    extends _$TypesystemStateCopyWithImpl<$Res>
    implements $TypesystemStateLoadedCopyWith<$Res> {
  _$TypesystemStateLoadedCopyWithImpl(
      TypesystemStateLoaded _value, $Res Function(TypesystemStateLoaded) _then)
      : super(_value, (v) => _then(v as TypesystemStateLoaded));

  @override
  TypesystemStateLoaded get _value => super._value as TypesystemStateLoaded;

  @override
  $Res call({
    Object? typesystem = freezed,
  }) {
    return _then(TypesystemStateLoaded(
      typesystem: typesystem == freezed
          ? _value.typesystem
          : typesystem // ignore: cast_nullable_to_non_nullable
              as Typesystem,
    ));
  }

  @override
  $TypesystemCopyWith<$Res> get typesystem {
    return $TypesystemCopyWith<$Res>(_value.typesystem, (value) {
      return _then(_value.copyWith(typesystem: value));
    });
  }
}

/// @nodoc

class _$TypesystemStateLoaded implements TypesystemStateLoaded {
  const _$TypesystemStateLoaded({required this.typesystem});

  @override
  final Typesystem typesystem;

  @override
  String toString() {
    return 'TypesystemState.loaded(typesystem: $typesystem)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is TypesystemStateLoaded &&
            (identical(other.typesystem, typesystem) ||
                other.typesystem == typesystem));
  }

  @override
  int get hashCode => Object.hash(runtimeType, typesystem);

  @JsonKey(ignore: true)
  @override
  $TypesystemStateLoadedCopyWith<TypesystemStateLoaded> get copyWith =>
      _$TypesystemStateLoadedCopyWithImpl<TypesystemStateLoaded>(
          this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Typesystem typesystem) loaded,
  }) {
    return loaded(typesystem);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
  }) {
    return loaded?.call(typesystem);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Typesystem typesystem)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(typesystem);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(TypesystemStateInitial value) initial,
    required TResult Function(TypesystemStateLoading value) loading,
    required TResult Function(TypesystemStateFailure value) failure,
    required TResult Function(TypesystemStateLoaded value) loaded,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(TypesystemStateInitial value)? initial,
    TResult Function(TypesystemStateLoading value)? loading,
    TResult Function(TypesystemStateFailure value)? failure,
    TResult Function(TypesystemStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class TypesystemStateLoaded implements TypesystemState {
  const factory TypesystemStateLoaded({required Typesystem typesystem}) =
      _$TypesystemStateLoaded;

  Typesystem get typesystem;
  @JsonKey(ignore: true)
  $TypesystemStateLoadedCopyWith<TypesystemStateLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}
