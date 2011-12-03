class Session:
	# Previously assigned session id, for use in unique ids
	last_id = 0

	def __init__(self, nickname="LazyPlayer"):
		self.nickname = nickname
		self.active = true
		self.session_id = Session.nextID()

	def nextID():
		last_id += 1
		return "s" + last_id

	def to_json(self):
		"""Convert to json representation"""
		json_dict = {}
		json_dict['session_id'] = self.session_id
		json_dict['nickname'] = self.nickname
		json_dict['game_id'] = self.game_id
		json_dict['active'] = self.active

		return json.dumps(json_dict)
