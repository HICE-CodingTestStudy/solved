def nosharp(s):
    s = s.replace('C#','c').replace('D#','d').replace('F#','f').replace('G#','g').replace('A#','a')
    return s

def tchange(t):	
    return int(t.split(':')[0]) * 60 + int(t.split(':')[1])

def solution(m, musicinfos):
    answer = []
    for info in musicinfos:
        info = info.split(',')
        info[3] = nosharp(info[3])
        T = tchange(info[1]) - tchange(info[0])	# 시간계산
        L = len(info[3])
        if T >= L:	# 시간이 길이보다 길면
            M = info[3] * (T//L) + info[3][:T%L]
        else:					# 시간이 길이보다 짧으면
            M = info[3][:T]

        if nosharp(m) in M:		# 들은음이 있으면
            answer.append([T, info[2]])

    if len(answer) == 0:
        return '(None)'
    else:
        return sorted(answer, key=lambda x: -x[0])[0][1]