from bottle import route, run

@route('/hello/:name')
def hello(name='world'):
	return "Hello " + name + "!"

run(host='localhost', port=8080)

