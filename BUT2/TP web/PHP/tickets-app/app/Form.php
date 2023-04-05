<?php

namespace app;

/**
 * Helper Form for creating forms easier
 */
class Form
{

    /**
     * Construct the string properties in an HTML tag
     *
     * @param array $data List of tag properties
     * @return string properties inject in tags
     */
    private function attributes(array $data):string
    {
        $attributes = '';
        foreach ($data as $key => $value) {
            if ($value === true) {
                $attributes .= ' ' . $key;
            }elseif ($value) {
                $attributes .= ' ' . $key . '="' . $value . '"';
            }
        }
        return $attributes;
    }

    /**
     * Open the form tag with properties passed in $parameters
     *
     * @param array $attributes List of properties in the HTML tag
     * @return string HTML open Form tag
     */
    public function open(array $attributes = []): string
    {
        $attributes = array_merge([
            'method' => 'post',
            'id' => 'form'
        ], $attributes);
        if (isset($attributes['type']) && $attributes['type'] === 'file'){
            $attributes['enctype'] = 'multipart/form-data';
            unset($attributes['type']);
        }
        return '<form' . $this->attributes($attributes) . '>';
    }

    /**
     * Create an HTML label tag
     *
     * @param string $title The displayed label
     * @param array $attributes list of tags properties
     * @return string HTML label Tag
     */
    public function label(string $title, array $attributes = []): string
    {
        return '<label ' . $this->attributes($attributes) . '>' . $title . '</label>';
    }

    /**
     * Create an HTML input tag
     *
     * @param string $name The name of input, used to name the input and create id suffixed by "-input"
     * @param array $attributes list of properties inn the input tag
     * @return string HTML input tag
     */
    public function input(string $name, array $attributes = []): string
    {
        $attributes = array_merge([
            'id' => $name . '-input',
            'label-id' => $name . '-label',
            'title' => $name,
            'type' => 'text',
            'label' => true,
        ], $attributes);
        $label = '';
        if ($attributes['label']){
            $labelAttributes = [
                'for' => $attributes['id'],
            ];
            foreach ($attributes as $key => $value){
                if (preg_match("/^label-/(.*)$/", $key, $match)){
                    $labelAttributes[$match[1]] = $value;
                    unset($attributes[$key]);
                }
            }
            $label = $this->label($attributes['title'], $labelAttributes);
            unset($attributes['title']);
        }
        return $label . '<input name="' . $name . '" ' . $this->attributes($attributes) . '>';
    }

    /**
     * create a button to submit form
     *
     * @param string $text The text displayed
     * @param array $attributes List of attributes needed in the button tag
     * @return string HTML button tag
     */
    public function button(string $text = "Record", array $attributes = []): string
    {
        $attributes = array_merge([
            'type' => 'submit'
        ], $attributes);
        return '<button ' . $this->attributes($attributes) . '>' . $text . '</button>';
    }

    /**
     * create the close form tag
     *
     * @param string $text the text in the submit button
     * @return string HTML close form tag
     */
    public function end(string $text = ''): string
    {
        return ($text?$this->button($text):'') . '</form>';
    }


}