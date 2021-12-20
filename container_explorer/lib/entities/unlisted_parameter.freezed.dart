// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'unlisted_parameter.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

UnlistedParameter _$UnlistedParameterFromJson(Map<String, dynamic> json) {
  return _UnlistedParameter.fromJson(json);
}

/// @nodoc
class _$UnlistedParameterTearOff {
  const _$UnlistedParameterTearOff();

  _UnlistedParameter call({required String name, required dynamic value}) {
    return _UnlistedParameter(
      name: name,
      value: value,
    );
  }

  UnlistedParameter fromJson(Map<String, Object?> json) {
    return UnlistedParameter.fromJson(json);
  }
}

/// @nodoc
const $UnlistedParameter = _$UnlistedParameterTearOff();

/// @nodoc
mixin _$UnlistedParameter {
  String get name => throw _privateConstructorUsedError;
  dynamic get value => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UnlistedParameterCopyWith<UnlistedParameter> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UnlistedParameterCopyWith<$Res> {
  factory $UnlistedParameterCopyWith(
          UnlistedParameter value, $Res Function(UnlistedParameter) then) =
      _$UnlistedParameterCopyWithImpl<$Res>;
  $Res call({String name, dynamic value});
}

/// @nodoc
class _$UnlistedParameterCopyWithImpl<$Res>
    implements $UnlistedParameterCopyWith<$Res> {
  _$UnlistedParameterCopyWithImpl(this._value, this._then);

  final UnlistedParameter _value;
  // ignore: unused_field
  final $Res Function(UnlistedParameter) _then;

  @override
  $Res call({
    Object? name = freezed,
    Object? value = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      value: value == freezed
          ? _value.value
          : value // ignore: cast_nullable_to_non_nullable
              as dynamic,
    ));
  }
}

/// @nodoc
abstract class _$UnlistedParameterCopyWith<$Res>
    implements $UnlistedParameterCopyWith<$Res> {
  factory _$UnlistedParameterCopyWith(
          _UnlistedParameter value, $Res Function(_UnlistedParameter) then) =
      __$UnlistedParameterCopyWithImpl<$Res>;
  @override
  $Res call({String name, dynamic value});
}

/// @nodoc
class __$UnlistedParameterCopyWithImpl<$Res>
    extends _$UnlistedParameterCopyWithImpl<$Res>
    implements _$UnlistedParameterCopyWith<$Res> {
  __$UnlistedParameterCopyWithImpl(
      _UnlistedParameter _value, $Res Function(_UnlistedParameter) _then)
      : super(_value, (v) => _then(v as _UnlistedParameter));

  @override
  _UnlistedParameter get _value => super._value as _UnlistedParameter;

  @override
  $Res call({
    Object? name = freezed,
    Object? value = freezed,
  }) {
    return _then(_UnlistedParameter(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      value: value == freezed
          ? _value.value
          : value // ignore: cast_nullable_to_non_nullable
              as dynamic,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_UnlistedParameter extends _UnlistedParameter {
  const _$_UnlistedParameter({required this.name, required this.value})
      : super._();

  factory _$_UnlistedParameter.fromJson(Map<String, dynamic> json) =>
      _$$_UnlistedParameterFromJson(json);

  @override
  final String name;
  @override
  final dynamic value;

  @override
  String toString() {
    return 'UnlistedParameter(name: $name, value: $value)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UnlistedParameter &&
            (identical(other.name, name) || other.name == name) &&
            const DeepCollectionEquality().equals(other.value, value));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, name, const DeepCollectionEquality().hash(value));

  @JsonKey(ignore: true)
  @override
  _$UnlistedParameterCopyWith<_UnlistedParameter> get copyWith =>
      __$UnlistedParameterCopyWithImpl<_UnlistedParameter>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UnlistedParameterToJson(this);
  }
}

abstract class _UnlistedParameter extends UnlistedParameter {
  const factory _UnlistedParameter(
      {required String name, required dynamic value}) = _$_UnlistedParameter;
  const _UnlistedParameter._() : super._();

  factory _UnlistedParameter.fromJson(Map<String, dynamic> json) =
      _$_UnlistedParameter.fromJson;

  @override
  String get name;
  @override
  dynamic get value;
  @override
  @JsonKey(ignore: true)
  _$UnlistedParameterCopyWith<_UnlistedParameter> get copyWith =>
      throw _privateConstructorUsedError;
}
