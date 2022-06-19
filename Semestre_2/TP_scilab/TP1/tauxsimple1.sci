function s=tauxsimple1(p,r,i)
    s=p;
    for j = 0:i-1
        s=s + interet(p,r);
    end
endfunction
