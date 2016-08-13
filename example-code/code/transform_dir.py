from transform import *
import os


def t_dir(source, ext):
    filelist = []
    summary = []
    summary_path = os.path.join(source, 'summary.csv')
    files = os.listdir(source)
    for filename in files:
        if not filename.endswith(ext):
            continue
        filelist.append(os.path.join(source, filename))
        summary.append(filename[:filename.index('.txt')] + ',' + filename + '.bmp\n')
    transfer(filelist, False)
    with open(summary_path, 'w') as f:
        f.writelines(summary)
    print summary_path, 'saved'

if __name__ == '__main__':
    import sys
    source_dir = sys.argv[1]
    ext = '.txt' if len(sys.argv) < 3 else sys.argv[2]
    t_dir(source_dir, ext)
