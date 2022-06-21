function [x,n]=secante(a,b,f,e,N)
     n=0;
    while abs(a-b)>= e & n<N
        x= -(f(a)*(a-b))/((f(a)-f(b)))+a;
        a=b;
        b=x;
        n=n+1;
    end
endfunction
