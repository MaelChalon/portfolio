function IT = trapeze(a,b,m,f)
     p=(b-a)/m;
     IT=0;
    for i = a:p:b-p
        IT=IT+((f(i)+f(i+p))*p)/2;
    end
endfunction
