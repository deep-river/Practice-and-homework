def findRhymes(filename, word):

	rhymingWords = []
	patterns = []

	with open(filename) as f:
		for line in f:
			splited = line.split(' ')
			if word == splited[0]:
				pos = 0
				for sound in splited:
					pos += 1
					if sound.endswith(('0', '1', '2')):
						pattern0 = [sound[:-1] + '0']
						pattern0.extend(splited[pos:])
						pattern1 = [sound[0:-1] + '1']
						pattern1.extend(splited[pos:])
						pattern2 = [sound[0:-1] + '2']
						pattern2.extend(splited[pos:])
						patterns = [" ".join(pattern0), " ".join(pattern1), " ".join(pattern2)]

	with open(filename) as f:
		for line in f:
			for pattern in patterns:
				if line.endswith(pattern):
					splited = line.split(' ')
					#if splited[0] != word:
					#	rhymingWords.append(splited[0])
					rhymingWords.append(splited[0])

	return rhymingWords

#print(findRhymes('cmudict-0.7b', 'ABYSS'))

#findRhymes('cmudict_test-0.7b', 'AARONS')

def isRhymeSounds(wordA, wordB):

	patternA = []
	patternB = []

	pos = 0
	for sound in wordA:
		#sound = str(sound)
		pos += 1
		if sound.endswith(('0', '1', '2')):
			pattern0 = [sound[:-1] + '0']
			pattern0.extend(wordA[pos:])
			pattern1 = [sound[:-1] + '1']
			pattern1.extend(wordA[pos:])
			pattern2 = [sound[:-1] + '2']
			pattern2.extend(wordA[pos:])
			patternA = [" ".join(pattern0), " ".join(pattern1), " ".join(pattern2)]

	pos = 0
	for sound in wordB:
		#sound = str(sound)
		pos += 1
		if sound.endswith(('0', '1', '2')):
			pattern0 = [sound[:-1] + '0']
			pattern0.extend(wordB[pos:])
			pattern1 = [sound[:-1] + '1']
			pattern1.extend(wordB[pos:])
			pattern2 = [sound[:-1] + '2']
			pattern2.extend(wordB[pos:])
			patternB = [" ".join(pattern0), " ".join(pattern1), " ".join(pattern2)]

	for soundsA in patternA:
		for soundsB in patternB:
			if soundsA == soundsB:
				return True
			else:
				return False

#print(isRhymeSounds(['AH0', 'B', 'IH1', 'S'], ['EH1', 'R', 'B', 'EY2', 'S', 'IH0', 'S']))

#print(isRhymeSounds(['AH0', 'B', 'IH1', 'S'], ['EH1', 'R', 'B', 'AH0', 'S']))
