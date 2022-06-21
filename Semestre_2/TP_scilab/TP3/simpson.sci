function IS = simpson(a,b,m,f)
    IS = 0;
    p=(b-a)/m;
    for i = a:p:b-p
        IS=IS+(p/6)*(f(i)+4*f((i+i+p)/2)+f(i+p));
    end
endfunction
