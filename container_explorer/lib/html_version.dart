import 'dart:html' as html;

String? get_url() {
  return html.window.location.hostname ?? '127.0.0.1';
}

int get_port() {
  return int.parse(html.window.location.port);
}
