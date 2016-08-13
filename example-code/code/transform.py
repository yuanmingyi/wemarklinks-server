from PIL import Image


def read_data(file_path):
    stream = []
    with open(file_path, 'r') as f:
        lines = f.readlines()
        row_count = len(lines)
        col_count = 0
        for r in range(0, row_count):
            line = lines[r]
            if len(line.strip()) == 0:
                continue
            cols = line.strip().split(' ')
            if col_count == 0:
                col_count = len(cols)
                print col_count, row_count
            count = 0
            t = 0
            while count < col_count:
                t = t * 2 + (1 if cols[count] == '1' else 0)
                count += 1
                if count % 8 == 0:
                    stream.append(chr(t))
                    t = 0
            if count % 8 != 0:
                while count % 8 != 0:
                    t *= 2
                    count += 1
                stream.append(chr(t))
    return ''.join(stream), (col_count, row_count)

def transfer(files, rawdata=True):
    for input in files:
        stream, size = read_data(input)
        print len(stream), size
        im = Image.fromstring('1', size, stream, 'raw', '1;I', 0, 1)
        im.save(input + '.bmp')
        if rawdata:
            with open(input + '.raw', 'wt') as f:
                f.write(stream)

if __name__ == '__main__':
    import sys
    transfer(sys.argv[1:])