function IR = rectangle(a,b,m,f)
    p=(b-a)/m;
    IR=0;
    for i=a:p:b-p
        IR=IR+(i-i+p)*f((i+p+i)/2);
    end
    
endfunction
