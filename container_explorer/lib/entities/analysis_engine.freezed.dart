// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'analysis_engine.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AnalysisEngine _$AnalysisEngineFromJson(Map<String, dynamic> json) {
  return _AnalysisEngine.fromJson(json);
}

/// @nodoc
class _$AnalysisEngineTearOff {
  const _$AnalysisEngineTearOff();

  _AnalysisEngine call(
      {required Annotator? annotator,
      required List<AnalysisEngine>? engines,
      required List<SofaMapping> sofa_mappings}) {
    return _AnalysisEngine(
      annotator: annotator,
      engines: engines,
      sofa_mappings: sofa_mappings,
    );
  }

  AnalysisEngine fromJson(Map<String, Object?> json) {
    return AnalysisEngine.fromJson(json);
  }
}

/// @nodoc
const $AnalysisEngine = _$AnalysisEngineTearOff();

/// @nodoc
mixin _$AnalysisEngine {
  Annotator? get annotator => throw _privateConstructorUsedError;
  List<AnalysisEngine>? get engines => throw _privateConstructorUsedError;
  List<SofaMapping> get sofa_mappings => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AnalysisEngineCopyWith<AnalysisEngine> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AnalysisEngineCopyWith<$Res> {
  factory $AnalysisEngineCopyWith(
          AnalysisEngine value, $Res Function(AnalysisEngine) then) =
      _$AnalysisEngineCopyWithImpl<$Res>;
  $Res call(
      {Annotator? annotator,
      List<AnalysisEngine>? engines,
      List<SofaMapping> sofa_mappings});

  $AnnotatorCopyWith<$Res>? get annotator;
}

/// @nodoc
class _$AnalysisEngineCopyWithImpl<$Res>
    implements $AnalysisEngineCopyWith<$Res> {
  _$AnalysisEngineCopyWithImpl(this._value, this._then);

  final AnalysisEngine _value;
  // ignore: unused_field
  final $Res Function(AnalysisEngine) _then;

  @override
  $Res call({
    Object? annotator = freezed,
    Object? engines = freezed,
    Object? sofa_mappings = freezed,
  }) {
    return _then(_value.copyWith(
      annotator: annotator == freezed
          ? _value.annotator
          : annotator // ignore: cast_nullable_to_non_nullable
              as Annotator?,
      engines: engines == freezed
          ? _value.engines
          : engines // ignore: cast_nullable_to_non_nullable
              as List<AnalysisEngine>?,
      sofa_mappings: sofa_mappings == freezed
          ? _value.sofa_mappings
          : sofa_mappings // ignore: cast_nullable_to_non_nullable
              as List<SofaMapping>,
    ));
  }

  @override
  $AnnotatorCopyWith<$Res>? get annotator {
    if (_value.annotator == null) {
      return null;
    }

    return $AnnotatorCopyWith<$Res>(_value.annotator!, (value) {
      return _then(_value.copyWith(annotator: value));
    });
  }
}

/// @nodoc
abstract class _$AnalysisEngineCopyWith<$Res>
    implements $AnalysisEngineCopyWith<$Res> {
  factory _$AnalysisEngineCopyWith(
          _AnalysisEngine value, $Res Function(_AnalysisEngine) then) =
      __$AnalysisEngineCopyWithImpl<$Res>;
  @override
  $Res call(
      {Annotator? annotator,
      List<AnalysisEngine>? engines,
      List<SofaMapping> sofa_mappings});

  @override
  $AnnotatorCopyWith<$Res>? get annotator;
}

/// @nodoc
class __$AnalysisEngineCopyWithImpl<$Res>
    extends _$AnalysisEngineCopyWithImpl<$Res>
    implements _$AnalysisEngineCopyWith<$Res> {
  __$AnalysisEngineCopyWithImpl(
      _AnalysisEngine _value, $Res Function(_AnalysisEngine) _then)
      : super(_value, (v) => _then(v as _AnalysisEngine));

  @override
  _AnalysisEngine get _value => super._value as _AnalysisEngine;

  @override
  $Res call({
    Object? annotator = freezed,
    Object? engines = freezed,
    Object? sofa_mappings = freezed,
  }) {
    return _then(_AnalysisEngine(
      annotator: annotator == freezed
          ? _value.annotator
          : annotator // ignore: cast_nullable_to_non_nullable
              as Annotator?,
      engines: engines == freezed
          ? _value.engines
          : engines // ignore: cast_nullable_to_non_nullable
              as List<AnalysisEngine>?,
      sofa_mappings: sofa_mappings == freezed
          ? _value.sofa_mappings
          : sofa_mappings // ignore: cast_nullable_to_non_nullable
              as List<SofaMapping>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_AnalysisEngine extends _AnalysisEngine {
  const _$_AnalysisEngine(
      {required this.annotator,
      required this.engines,
      required this.sofa_mappings})
      : super._();

  factory _$_AnalysisEngine.fromJson(Map<String, dynamic> json) =>
      _$$_AnalysisEngineFromJson(json);

  @override
  final Annotator? annotator;
  @override
  final List<AnalysisEngine>? engines;
  @override
  final List<SofaMapping> sofa_mappings;

  @override
  String toString() {
    return 'AnalysisEngine(annotator: $annotator, engines: $engines, sofa_mappings: $sofa_mappings)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AnalysisEngine &&
            (identical(other.annotator, annotator) ||
                other.annotator == annotator) &&
            const DeepCollectionEquality().equals(other.engines, engines) &&
            const DeepCollectionEquality()
                .equals(other.sofa_mappings, sofa_mappings));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      annotator,
      const DeepCollectionEquality().hash(engines),
      const DeepCollectionEquality().hash(sofa_mappings));

  @JsonKey(ignore: true)
  @override
  _$AnalysisEngineCopyWith<_AnalysisEngine> get copyWith =>
      __$AnalysisEngineCopyWithImpl<_AnalysisEngine>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AnalysisEngineToJson(this);
  }
}

abstract class _AnalysisEngine extends AnalysisEngine {
  const factory _AnalysisEngine(
      {required Annotator? annotator,
      required List<AnalysisEngine>? engines,
      required List<SofaMapping> sofa_mappings}) = _$_AnalysisEngine;
  const _AnalysisEngine._() : super._();

  factory _AnalysisEngine.fromJson(Map<String, dynamic> json) =
      _$_AnalysisEngine.fromJson;

  @override
  Annotator? get annotator;
  @override
  List<AnalysisEngine>? get engines;
  @override
  List<SofaMapping> get sofa_mappings;
  @override
  @JsonKey(ignore: true)
  _$AnalysisEngineCopyWith<_AnalysisEngine> get copyWith =>
      throw _privateConstructorUsedError;
}
