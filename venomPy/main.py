from http.server import BaseHTTPRequestHandler, HTTPServer
from math import sin, cos, tan, asin, acos, atan
import urllib.parse
import time
import re

PORT = 1002

class RequestHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        content_len = int(self.headers.get('Content-Length'))
        post_body = str(self.rfile.read(content_len))
        raw = post_body.split("=", 1)[1];
        raw = urllib.parse.unquote(raw)
        ex = raw.replace("'", "");

        print(ex)
        start = time.time()
        result = eval(ex)
        end = time.time()
        elapsed = end - start;

        to_sent = str(result) + ' t: ' + str(elapsed) + ' ms';
        print(to_sent)

        b = bytes(to_sent, 'utf-8')
        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(b).encode(encoding='utf_8')


httpd = HTTPServer(('localhost', PORT), RequestHandler)
httpd.serve_forever()
