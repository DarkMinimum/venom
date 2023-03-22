from http.server import BaseHTTPRequestHandler, HTTPServer
import math
import urllib.parse

PORT = 1002

class RequestHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        content_len = int(self.headers.get('Content-Length'))
        post_body = str(self.rfile.read(content_len))
        raw = post_body.split("=",1)[1];
        raw = urllib.parse.unquote(raw)
        ex = raw.replace("'", "");
        print(ex)
        result = eval(ex);

        print(result)

        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(str(result).encode(encoding='utf_8'))

httpd = HTTPServer(('localhost', PORT), RequestHandler)
httpd.serve_forever()
