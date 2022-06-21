for i=0:4
    disp(string (simpson(0,1,2^i,f1)));
    disp(string (log(2)-simpson(0,1,2^i,f1)));
end
