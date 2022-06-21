function x=dichotomie1(a,b,f,n)
    for i=0:n
        x= (b+a)/2;
        if f(a)*f(x)<0
            b=(b+a)/2;
        else
            a = (b+a)/2;
        end
    end
endfunction
