import unittest
from rhymes import RhymingDictionary


class TestIsRhymeSounds(unittest.TestCase):

	def testIsRhymeSounds(self):

		A = ['AH0', 'B', 'IH1', 'S']#ABYSS
		B = ['EH1', 'R', 'B', 'EY2', 'S', 'IH0', 'S']#AIRBASES
		C = ['EH1', 'R', 'B', 'AH0', 'S']#AIRBUS
		D = ['EH1', 'R', 'B', 'AH0', 'S', 'IH0', 'Z']#AIRBUS'S
		E = []
		F = ['S', 'S', 'S', 'S']
		#G = [1, 2, 3, 4, 5]

		self.assertTrue(RhymingDictionary.isRhymeSounds(A, B))
		self.assertFalse(RhymingDictionary.isRhymeSounds(A, C))
		self.assertFalse(RhymingDictionary.isRhymeSounds(A, D))
		self.assertFalse(RhymingDictionary.isRhymeSounds(A, E))
		self.assertFalse(RhymingDictionary.isRhymeSounds(A, F))
		#self.assertFalse(RhymingDictionary.isRhymeSounds(A, G))


if __name__ == '__main__':

	unittest.main()
