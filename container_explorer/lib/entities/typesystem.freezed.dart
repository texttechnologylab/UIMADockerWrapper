// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'typesystem.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

Typesystem _$TypesystemFromJson(Map<String, dynamic> json) {
  return _Typesystem.fromJson(json);
}

/// @nodoc
class _$TypesystemTearOff {
  const _$TypesystemTearOff();

  _Typesystem call({required List<UIMAType> typesystem}) {
    return _Typesystem(
      typesystem: typesystem,
    );
  }

  Typesystem fromJson(Map<String, Object?> json) {
    return Typesystem.fromJson(json);
  }
}

/// @nodoc
const $Typesystem = _$TypesystemTearOff();

/// @nodoc
mixin _$Typesystem {
  List<UIMAType> get typesystem => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $TypesystemCopyWith<Typesystem> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $TypesystemCopyWith<$Res> {
  factory $TypesystemCopyWith(
          Typesystem value, $Res Function(Typesystem) then) =
      _$TypesystemCopyWithImpl<$Res>;
  $Res call({List<UIMAType> typesystem});
}

/// @nodoc
class _$TypesystemCopyWithImpl<$Res> implements $TypesystemCopyWith<$Res> {
  _$TypesystemCopyWithImpl(this._value, this._then);

  final Typesystem _value;
  // ignore: unused_field
  final $Res Function(Typesystem) _then;

  @override
  $Res call({
    Object? typesystem = freezed,
  }) {
    return _then(_value.copyWith(
      typesystem: typesystem == freezed
          ? _value.typesystem
          : typesystem // ignore: cast_nullable_to_non_nullable
              as List<UIMAType>,
    ));
  }
}

/// @nodoc
abstract class _$TypesystemCopyWith<$Res> implements $TypesystemCopyWith<$Res> {
  factory _$TypesystemCopyWith(
          _Typesystem value, $Res Function(_Typesystem) then) =
      __$TypesystemCopyWithImpl<$Res>;
  @override
  $Res call({List<UIMAType> typesystem});
}

/// @nodoc
class __$TypesystemCopyWithImpl<$Res> extends _$TypesystemCopyWithImpl<$Res>
    implements _$TypesystemCopyWith<$Res> {
  __$TypesystemCopyWithImpl(
      _Typesystem _value, $Res Function(_Typesystem) _then)
      : super(_value, (v) => _then(v as _Typesystem));

  @override
  _Typesystem get _value => super._value as _Typesystem;

  @override
  $Res call({
    Object? typesystem = freezed,
  }) {
    return _then(_Typesystem(
      typesystem: typesystem == freezed
          ? _value.typesystem
          : typesystem // ignore: cast_nullable_to_non_nullable
              as List<UIMAType>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_Typesystem extends _Typesystem {
  const _$_Typesystem({required this.typesystem}) : super._();

  factory _$_Typesystem.fromJson(Map<String, dynamic> json) =>
      _$$_TypesystemFromJson(json);

  @override
  final List<UIMAType> typesystem;

  @override
  String toString() {
    return 'Typesystem(typesystem: $typesystem)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _Typesystem &&
            const DeepCollectionEquality()
                .equals(other.typesystem, typesystem));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, const DeepCollectionEquality().hash(typesystem));

  @JsonKey(ignore: true)
  @override
  _$TypesystemCopyWith<_Typesystem> get copyWith =>
      __$TypesystemCopyWithImpl<_Typesystem>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_TypesystemToJson(this);
  }
}

abstract class _Typesystem extends Typesystem {
  const factory _Typesystem({required List<UIMAType> typesystem}) =
      _$_Typesystem;
  const _Typesystem._() : super._();

  factory _Typesystem.fromJson(Map<String, dynamic> json) =
      _$_Typesystem.fromJson;

  @override
  List<UIMAType> get typesystem;
  @override
  @JsonKey(ignore: true)
  _$TypesystemCopyWith<_Typesystem> get copyWith =>
      throw _privateConstructorUsedError;
}
