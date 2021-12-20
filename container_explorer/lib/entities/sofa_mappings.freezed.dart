// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'sofa_mappings.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

SofaMapping _$SofaMappingFromJson(Map<String, dynamic> json) {
  return _SofaMapping.fromJson(json);
}

/// @nodoc
class _$SofaMappingTearOff {
  const _$SofaMappingTearOff();

  _SofaMapping call(
      {required String component_sofa, required String aggregate_sofa}) {
    return _SofaMapping(
      component_sofa: component_sofa,
      aggregate_sofa: aggregate_sofa,
    );
  }

  SofaMapping fromJson(Map<String, Object?> json) {
    return SofaMapping.fromJson(json);
  }
}

/// @nodoc
const $SofaMapping = _$SofaMappingTearOff();

/// @nodoc
mixin _$SofaMapping {
  String get component_sofa => throw _privateConstructorUsedError;
  String get aggregate_sofa => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $SofaMappingCopyWith<SofaMapping> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $SofaMappingCopyWith<$Res> {
  factory $SofaMappingCopyWith(
          SofaMapping value, $Res Function(SofaMapping) then) =
      _$SofaMappingCopyWithImpl<$Res>;
  $Res call({String component_sofa, String aggregate_sofa});
}

/// @nodoc
class _$SofaMappingCopyWithImpl<$Res> implements $SofaMappingCopyWith<$Res> {
  _$SofaMappingCopyWithImpl(this._value, this._then);

  final SofaMapping _value;
  // ignore: unused_field
  final $Res Function(SofaMapping) _then;

  @override
  $Res call({
    Object? component_sofa = freezed,
    Object? aggregate_sofa = freezed,
  }) {
    return _then(_value.copyWith(
      component_sofa: component_sofa == freezed
          ? _value.component_sofa
          : component_sofa // ignore: cast_nullable_to_non_nullable
              as String,
      aggregate_sofa: aggregate_sofa == freezed
          ? _value.aggregate_sofa
          : aggregate_sofa // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$SofaMappingCopyWith<$Res>
    implements $SofaMappingCopyWith<$Res> {
  factory _$SofaMappingCopyWith(
          _SofaMapping value, $Res Function(_SofaMapping) then) =
      __$SofaMappingCopyWithImpl<$Res>;
  @override
  $Res call({String component_sofa, String aggregate_sofa});
}

/// @nodoc
class __$SofaMappingCopyWithImpl<$Res> extends _$SofaMappingCopyWithImpl<$Res>
    implements _$SofaMappingCopyWith<$Res> {
  __$SofaMappingCopyWithImpl(
      _SofaMapping _value, $Res Function(_SofaMapping) _then)
      : super(_value, (v) => _then(v as _SofaMapping));

  @override
  _SofaMapping get _value => super._value as _SofaMapping;

  @override
  $Res call({
    Object? component_sofa = freezed,
    Object? aggregate_sofa = freezed,
  }) {
    return _then(_SofaMapping(
      component_sofa: component_sofa == freezed
          ? _value.component_sofa
          : component_sofa // ignore: cast_nullable_to_non_nullable
              as String,
      aggregate_sofa: aggregate_sofa == freezed
          ? _value.aggregate_sofa
          : aggregate_sofa // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_SofaMapping extends _SofaMapping {
  const _$_SofaMapping(
      {required this.component_sofa, required this.aggregate_sofa})
      : super._();

  factory _$_SofaMapping.fromJson(Map<String, dynamic> json) =>
      _$$_SofaMappingFromJson(json);

  @override
  final String component_sofa;
  @override
  final String aggregate_sofa;

  @override
  String toString() {
    return 'SofaMapping(component_sofa: $component_sofa, aggregate_sofa: $aggregate_sofa)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _SofaMapping &&
            (identical(other.component_sofa, component_sofa) ||
                other.component_sofa == component_sofa) &&
            (identical(other.aggregate_sofa, aggregate_sofa) ||
                other.aggregate_sofa == aggregate_sofa));
  }

  @override
  int get hashCode => Object.hash(runtimeType, component_sofa, aggregate_sofa);

  @JsonKey(ignore: true)
  @override
  _$SofaMappingCopyWith<_SofaMapping> get copyWith =>
      __$SofaMappingCopyWithImpl<_SofaMapping>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_SofaMappingToJson(this);
  }
}

abstract class _SofaMapping extends SofaMapping {
  const factory _SofaMapping(
      {required String component_sofa,
      required String aggregate_sofa}) = _$_SofaMapping;
  const _SofaMapping._() : super._();

  factory _SofaMapping.fromJson(Map<String, dynamic> json) =
      _$_SofaMapping.fromJson;

  @override
  String get component_sofa;
  @override
  String get aggregate_sofa;
  @override
  @JsonKey(ignore: true)
  _$SofaMappingCopyWith<_SofaMapping> get copyWith =>
      throw _privateConstructorUsedError;
}
