val puzzle = listOf(
    "111110110111",
    "110011001101",
    "110100000011",
    "100011000101",
    "110010010000",
    "100110100101",
    "111000010101",
    "011110011001",
    "111001110111",
    "101001101001",
    "110001011001",
    "110110110100",
    "101010101100",
    "011010010010",
    "101101110000",
    "011101000111",
    "100100100100",
    "100101001111",
    "010101101011",
    "101101000001",
    "111111000100",
    "010110001000",
    "001111010010",
    "100010000100",
    "000110011001",
    "001111001000",
    "011101101111",
    "011001100101",
    "001001011111",
    "100011110001",
    "110010111000",
    "000101000010",
    "000011010100",
    "010011111000",
    "010101101100",
    "111110110101",
    "100011100100",
    "000100010010",
    "000111110111",
    "010110000111",
    "110110010101",
    "000011001011",
    "001100000101",
    "100010101100",
    "101000111001",
    "000010100100",
    "000011000001",
    "010100011101",
    "101110111001",
    "110001101101",
    "100000101111",
    "010000100101",
    "110001110110",
    "111101011101",
    "100100000101",
    "110011101110",
    "100001011001",
    "101011111000",
    "011101110000",
    "100100011101",
    "101010110011",
    "010010101100",
    "100010010101",
    "010000001001",
    "011100001000",
    "111011000000",
    "000101111001",
    "111001101001",
    "111111101111",
    "010000110001",
    "000011011101",
    "001010011001",
    "010010100110",
    "100011100011",
    "101010000010",
    "100111001011",
    "010000010011",
    "110111110110",
    "010111011110",
    "101101000010",
    "011011011010",
    "101000100000",
    "011001110011",
    "011001010010",
    "100101110010",
    "011011001010",
    "001010101101",
    "011101010011",
    "111101101011",
    "100011101010",
    "101101010110",
    "000010100011",
    "010100101000",
    "001000100101",
    "101111011010",
    "011110011011",
    "101000010001",
    "110001011100",
    "001010001010",
    "011010001100",
    "100000100100",
    "010110110111",
    "111010000001",
    "111111010010",
    "010111001010",
    "111000010110",
    "111110010011",
    "110001100000",
    "000111010101",
    "010001000101",
    "001100100011",
    "110010000011",
    "101100001011",
    "100100011110",
    "101110010001",
    "101001100010",
    "011001111001",
    "011000000101",
    "111100101011",
    "000101011011",
    "111110001000",
    "010111110101",
    "101010101110",
    "001110110111",
    "110000110110",
    "110001000011",
    "000010000110",
    "100100100011",
    "010101110111",
    "110000101001",
    "101100001110",
    "101101001001",
    "100011001101",
    "111010101100",
    "011100111001",
    "011001111010",
    "000010111101",
    "110010000010",
    "001111111100",
    "001011001000",
    "001110110001",
    "101100011100",
    "101011000110",
    "101010010110",
    "011000101011",
    "111101000110",
    "110011010011",
    "110000011001",
    "011000101110",
    "000001111001",
    "111011011001",
    "010111111010",
    "010011000110",
    "000101111100",
    "111101101001",
    "101001111001",
    "010000011001",
    "111111100111",
    "010101101000",
    "000100110010",
    "000101100010",
    "011001000010",
    "100010000000",
    "111100001100",
    "101110110011",
    "001100101001",
    "111001000001",
    "110101111111",
    "000100110001",
    "101000001011",
    "100110001000",
    "101001000111",
    "011010100001",
    "011001101111",
    "000111111011",
    "001111000100",
    "001001000101",
    "110010001011",
    "010110010110",
    "111000101110",
    "100100001001",
    "100101011010",
    "111111110101",
    "111100010010",
    "101110111101",
    "010100011011",
    "001101111001",
    "010010100111",
    "101111010000",
    "000011111101",
    "101110011101",
    "111000011111",
    "001000000100",
    "100011001010",
    "101111101000",
    "001110010001",
    "100110111011",
    "101011110000",
    "110111001111",
    "001000010010",
    "101011111111",
    "100111111111",
    "001011010100",
    "110100111111",
    "111001010110",
    "101001110001",
    "101010111110",
    "010100011010",
    "100000000001",
    "000010010101",
    "010010101010",
    "011101001011",
    "110101010110",
    "001110000000",
    "010111111101",
    "011101110101",
    "001010110010",
    "001111101111",
    "100011110110",
    "101100110100",
    "111010111010",
    "001110000011",
    "111101111110",
    "001111100110",
    "000010010110",
    "111110011011",
    "111110011000",
    "001001111101",
    "000011110110",
    "001000101010",
    "000110101111",
    "010010111100",
    "100010101000",
    "000111010000",
    "110000001001",
    "000101111011",
    "100100101000",
    "101001110000",
    "110001001010",
    "110011000001",
    "111010100110",
    "000000100011",
    "111000111010",
    "100100111000",
    "100010010100",
    "111011110100",
    "001001001011",
    "000010110001",
    "000000101110",
    "010010101011",
    "000100101000",
    "011000000100",
    "110111110111",
    "111100001011",
    "001000000011",
    "101010011010",
    "110110011010",
    "000011000101",
    "110110110001",
    "000111010011",
    "011010111101",
    "000101011110",
    "110111111111",
    "111010101110",
    "001101000011",
    "101100000101",
    "010001111001",
    "101001010100",
    "010111100010",
    "011100101100",
    "001100110111",
    "000101010000",
    "000001110100",
    "100101001010",
    "000010110000",
    "011101110110",
    "000000110000",
    "001001000110",
    "100000111000",
    "011101111101",
    "111011001010",
    "100010001100",
    "001100000001",
    "101100111011",
    "100010011110",
    "011011111010",
    "001000000110",
    "010001001111",
    "010101010110",
    "000100000000",
    "000110111000",
    "001010011111",
    "010000001111",
    "111001000111",
    "010001100101",
    "100011011000",
    "111111000001",
    "111010111110",
    "000011010101",
    "001000101100",
    "001000110101",
    "111101101100",
    "000011100100",
    "010110111111",
    "101010001000",
    "010001111011",
    "101110000111",
    "000111010111",
    "011011111100",
    "010100000011",
    "100010111001",
    "110100110001",
    "100001100111",
    "110000101011",
    "001000101011",
    "101100101000",
    "001001010100",
    "011000111111",
    "111000001010",
    "111100101000",
    "010100110100",
    "000011010001",
    "110001100110",
    "110101111101",
    "101010100000",
    "101110111110",
    "110101011001",
    "111101111001",
    "100111101110",
    "010011101000",
    "100000001010",
    "000100100101",
    "010000000000",
    "100000010110",
    "110100011000",
    "101100101010",
    "011000001111",
    "001111110111",
    "011010011010",
    "001100010001",
    "110000101111",
    "011101111010",
    "110010010110",
    "001110101010",
    "000100111010",
    "011100000001",
    "011011110010",
    "001000001101",
    "111110111011",
    "110100110100",
    "010111011000",
    "110111101111",
    "010110000010",
    "111011011010",
    "000001001100",
    "010010111101",
    "001110111010",
    "010100000010",
    "111000110110",
    "110001111101",
    "100110000001",
    "100001011111",
    "010110110100",
    "011000111000",
    "110101001101",
    "100010000010",
    "010010000110",
    "111111011111",
    "101111000011",
    "101010010101",
    "000000010001",
    "000010001010",
    "101111000010",
    "010001000001",
    "100110011011",
    "010111100000",
    "101111000101",
    "101111001110",
    "011010110010",
    "000101101110",
    "111000011010",
    "011101101001",
    "001010101110",
    "111101101110",
    "000001110111",
    "001111001001",
    "110010101111",
    "010111001110",
    "101001001010",
    "011110001000",
    "110111011010",
    "010001010010",
    "001101001000",
    "001110100001",
    "010011110110",
    "110010110101",
    "110000011101",
    "111011101111",
    "000000001100",
    "101010111111",
    "011110010011",
    "001101011111",
    "100000100101",
    "011010100111",
    "011010110000",
    "000100010001",
    "011111001101",
    "111110010111",
    "110001001101",
    "101111011000",
    "111010100000",
    "111001111001",
    "110000010111",
    "010010110110",
    "110110010000",
    "100101101010",
    "101010000000",
    "100010010001",
    "010110000110",
    "000000011000",
    "001011111110",
    "010000010111",
    "001001011110",
    "010100000100",
    "011011010110",
    "110010100000",
    "110001010101",
    "000110010111",
    "101001100000",
    "101111001000",
    "010100110110",
    "100101011110",
    "111010010001",
    "110101101011",
    "111111011101",
    "010000101111",
    "011100000000",
    "010011111110",
    "110000111111",
    "001110001101",
    "101001001011",
    "111011010001",
    "011101101100",
    "110010001111",
    "100100100110",
    "100011000011",
    "000010111000",
    "111101001000",
    "100001101111",
    "001011011001",
    "111101011011",
    "111110010100",
    "100000011110",
    "100110001011",
    "111100011001",
    "111111110000",
    "100100111001",
    "010001111110",
    "100110110010",
    "000000001101",
    "011110110100",
    "111011000101",
    "101001000101",
    "110010101001",
    "110011110111",
    "000010010011",
    "010011010010",
    "110101100000",
    "111000000010",
    "010100100000",
    "110000111010",
    "011101110111",
    "010011010100",
    "000110101001",
    "000110011100",
    "101000000101",
    "101101110111",
    "010101100110",
    "000001111000",
    "110100111100",
    "001101010010",
    "110111111011",
    "010010001101",
    "001111000011",
    "101111011110",
    "010110101011",
    "110011100011",
    "101000000000",
    "011000001000",
    "101101100111",
    "000100111011",
    "101101000111",
    "110001000111",
    "001010100011",
    "001011101101",
    "000110011010",
    "101011100000",
    "111110100001",
    "111110000010",
    "001111100101",
    "011011011101",
    "000111100001",
    "101001100101",
    "110000100100",
    "111000111011",
    "101011001110",
    "100111100011",
    "000100100000",
    "000111001110",
    "001000011001",
    "001101100101",
    "111000001110",
    "011011110101",
    "010001011100",
    "000000110100",
    "011010001111",
    "000011100111",
    "001100111101",
    "110010110011",
    "000100010111",
    "100000010001",
    "000001100000",
    "111101000100",
    "110100101010",
    "011010110011",
    "010101000010",
    "101100111100",
    "100001101001",
    "001011101111",
    "110100010001",
    "011001001001",
    "110010100010",
    "001000110001",
    "100111011110",
    "100111110011",
    "100101111010",
    "100000110011",
    "111000111110",
    "111111011010",
    "011010111011",
    "001101001011",
    "001110101100",
    "010110111101",
    "001101110111",
    "000010101101",
    "110110101001",
    "101001000010",
    "001101100111",
    "100111011000",
    "001101101110",
    "110001111001",
    "101111100000",
    "000110001110",
    "111001111100",
    "110101011110",
    "000001010010",
    "100001011101",
    "001110000101",
    "011000110110",
    "000000111100",
    "010100100110",
    "000110001001",
    "010011111101",
    "101010000111",
    "001001111011",
    "101101010111",
    "111101100011",
    "111001111110",
    "000010111100",
    "110000101000",
    "011100100010",
    "101100111010",
    "010001010101",
    "001010011100",
    "011001011000",
    "001111001011",
    "100100110111",
    "001001111100",
    "110010001110",
    "101010000110",
    "011000111011",
    "010001011110",
    "100011000010",
    "110100101111",
    "101111110110",
    "111000110000",
    "100100110011",
    "110110001111",
    "100000111100",
    "011001000000",
    "110000000110",
    "100011101011",
    "000000110011",
    "111101101000",
    "111010000110",
    "101111011001",
    "011011010011",
    "111001010011",
    "100111010001",
    "011000010110",
    "111110110011",
    "100010101111",
    "101111100100",
    "010000111000",
    "000011111011",
    "011111001001",
    "000001110001",
    "000001001111",
    "110000110111",
    "100110100000",
    "101100011110",
    "000110111101",
    "011000001010",
    "010011100011",
    "111111011000",
    "000000000001",
    "011110000101",
    "011001001000",
    "001010111010",
    "111011000111",
    "010111010110",
    "011011110111",
    "000011111001",
    "001101110001",
    "100110010000",
    "010100011111",
    "101100111110",
    "110100011001",
    "101011100001",
    "110101011111",
    "111111001110",
    "010011110101",
    "010101000011",
    "010011110000",
    "101110110001",
    "000100001111",
    "110011011000",
    "111100110000",
    "111110110110",
    "110111010100",
    "110011110001",
    "100100101011",
    "000101001010",
    "111110001001",
    "001100110010",
    "101011100101",
    "101110101101",
    "001000101110",
    "100110001001",
    "011001111000",
    "111111000010",
    "011001110111",
    "110100001000",
    "101101101000",
    "110010011011",
    "111111010001",
    "101001110010",
    "101010001110",
    "100101000000",
    "100001011100",
    "011011000101",
    "001001001001",
    "010110000011",
    "010111110111",
    "000000111011",
    "110110011100",
    "110000110011",
    "011110101000",
    "110110110110",
    "001101000100",
    "101110101011",
    "101101001110",
    "000110100110",
    "001100101110",
    "011010011001",
    "011111001111",
    "010010000001",
    "111110101001",
    "111000000101",
    "001010000110",
    "000100111110",
    "000001101101",
    "111010100001",
    "010110111010",
    "110000010001",
    "010001101011",
    "110000010010",
    "000001011011",
    "111001011000",
    "011000001101",
    "101101110001",
    "100000101110",
    "000010110100",
    "100001111001",
    "100010110110",
    "101011111001",
    "111101011010",
    "110100000100",
    "101111000100",
    "101101110011",
    "000011110011",
    "110000010101",
    "011000110011",
    "001100010110",
    "100101011101",
    "000100101011",
    "101011111101",
    "111000111100",
    "001000011011",
    "101000001010",
    "010001001000",
    "110111110001",
    "101010010111",
    "101100100010",
    "010001000100",
    "000110100010",
    "011111010000",
    "000110100001",
    "010010001001",
    "001001010001",
    "111010111100",
    "101110001101",
    "101011111011",
    "011101010101",
    "110010100001",
    "110101110110",
    "111000111000",
    "110111010010",
    "110101010111",
    "010101101010",
    "110101010010",
    "000000101011",
    "111101000001",
    "100011001110",
    "111110000011",
    "100111000100",
    "010010011101",
    "010100000111",
    "001111111110",
    "011001100001",
    "101000101000",
    "001111111010",
    "110001101011",
    "110110010111",
    "010110111100",
    "110100010111",
    "110101110001",
    "010011100111",
    "100101011011",
    "110111111100",
    "010011111100",
    "010000100111",
    "010111101110",
    "101010010100",
    "111001011101",
    "101111110101",
    "001101110011",
    "110111101110",
    "110100001010",
    "000111101001",
    "011111001010",
    "110110000000",
    "100110010111",
    "000101001111",
    "011001010011",
    "000000110001",
    "110110101000",
    "101001010000",
    "000010010010",
    "000011001111",
    "011010100110",
    "010101011011",
    "100100111011",
    "011110010110",
    "100001010001",
    "111010111101",
    "110100011011",
    "011101010001",
    "110011111011",
    "101100110101",
    "100001001101",
    "011111111111",
    "111111100100",
    "111100111101",
    "111011010110",
    "100101101100",
    "001011111101",
    "100101110111",
    "001100100010",
    "001011100010",
    "101010100111",
    "010111110011",
    "011111011011",
    "100011110100",
    "011011001111",
    "000101101111",
    "010011010111",
    "011001111101",
    "101101101101",
    "000010100101",
    "110101100110",
    "100101001000",
    "111110101000",
    "100101101011",
    "010101101101",
    "010001101000",
    "010111100110",
    "010111010100",
    "101110010000",
    "010010011100",
    "011001110000",
    "111010110001",
    "100000000000",
    "101111101100",
    "000100011100",
    "011010100011",
    "101111010010",
    "010000111010",
    "100100100000",
    "000111101100",
    "001000010110",
    "010011011000",
    "001101000010",
    "001000001110",
    "011011011000",
    "111110000000",
    "010100101010",
    "111111101100",
    "001101001110",
    "011001001101",
    "100100110100",
    "000010011001",
    "010000111110",
    "101111110100",
    "001100101100",
    "011111010111",
    "100010010010",
    "011001010001",
    "111011001000",
    "011011010100",
    "000101011101",
    "010001110000",
    "001000011000",
    "100111010111",
    "000001010101",
    "010100111010",
    "001000000010",
    "001100011011",
    "101110001011",
    "010000001110",
    "001011011101",
    "000100100001",
    "001010010001",
    "110100101001",
    "000011110101",
    "010110111011",
    "011010101100",
    "101110011110",
    "100001001010",
    "111100101111",
    "011010001101",
    "001111111000",
    "000011000111",
    "001100010011",
    "101001111101",
    "111000011000",
    "110111110011",
    "110110100011",
    "011110001010",
    "001111011110",
    "000101000001",
    "101101011110",
    "110000111101",
    "101011100011",
    "111011111100",
    "010011111010",
    "010101011000",
    "111110111101",
    "111011000110",
    "111011010000",
    "110001111110",
    "011110101101",
    "010010010000",
    "011011100011",
    "001100111110",
    "100101010100",
    "010010100001",
    "111100011010",
    "000010110011",
    "100000011100",
    "011111111010",
    "111111111010",
    "000110000011",
    "011001111100",
    "100100010001",
    "110111001011",
    "100101010010",
    "010110100110",
    "111011100000",
    "100100100010",
    "110111000010",
    "100001011010",
    "000110010010",
    "010000011111",
    "010001110100",
    "011001010111",
    "110111100100",
    "001011100100",
    "110001111011",
    "011011001000",
    "101110010101",
    "101001010101",
    "111100000000",
    "100110010010",
    "110010111100",
    "101110111100",
    "111001100110",
    "100111101100",
    "111011111010",
    "111000100011",
    "001001100011",
    "011110111000",
    "000111101010",
    "011011110001",
    "011111101100",
    "110111001010",
    "101111111010",
    "001010010011",
    "100101111110",
    "011011100010",
    "100111101101",
    "000010110111",
    "101010000011",
    "001100111001",
    "001101011001",
    "110010011000",
    "101100101100",
    "111000000111",
    "111110011111",
    "101010110010",
    "010011001110",
    "111101001001",
    "001011001001",
    "100111011111",
    "110100001111",
    "001111001100",
    "110101100001",
    "011001100000",
    "001100101000",
    "111111111111",
    "100110000000",
    "000100000011",
    "110001001111",
    "101000111110",
    "101111100101",
    "101000110100",
    "110100111000",
    "100010011000",
    "110011100010",
    "101010110100",
    "110000100010",
    "010100000000",
    "111011111011",
    "001110000111",
    "000110110010",
    "010010001000",
    "110110011110",
    "001111111101",
    "100100001111",
    "100110110100",
    "011110000111",
    "111101010011",
    "011000100000",
    "001011010010",
    "011001111111",
    "110010011101",
    "000001000110",
    "101011010001",
    "101101011111",
    "011101001111",
    "011010000011",
    "011010001010",
    "000010011101",
    "010010110011",
    "111100111010",
    "111011010101",
    "111110101010",
    "010000100011",
    "011000100101",
    "010010110000",
    "010100110001",
    "111110110000",
    "100010001000",
    "000011100011",
    "100000111101",
    "111001010101",
    "100000100001",
    "001011111000",
    "111100000001",
    "110001111010"
)