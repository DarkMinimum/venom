from http.server import BaseHTTPRequestHandler, HTTPServer
import math

PORT = 1002

# TODO: mapper
class RequestHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        content_len = int(self.headers.get('Content-Length'))
        post_body = self.rfile.read(content_len)

        print(post_body)
        result = eval(post_body);
        print(result)

        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(str(result).encode(encoding='utf_8'))

httpd = HTTPServer(('localhost', PORT), RequestHandler)
httpd.serve_forever()
