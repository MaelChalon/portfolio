<?php

if (!empty($_POST['login']) && !empty($_POST['password'])) {

    /** @var \app\Database $database */
    $user = $database->getOne('users', [ 'condition' =>
        [
            'name' => $_POST['login'],
            'password' => $_POST['password'],
        ]
    ]);
    if ($user) {
        $_SESSION['user'] = $user;
    }
}
header('Location: /');
exit();