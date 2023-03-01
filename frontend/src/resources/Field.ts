import {IResourceItem} from "@pankod/refine-core";

export type Field = {
    key: string;
    type: FieldType;
    displayName?: string;

    displayOnly?: Page[];
    relation?: boolean;
    multiple?: boolean;
    fieldable?: boolean;
    /**
     * Accessor to get the value from the record, if its an array it means multiple values should be concatenated
     */
    accessor?: string | string[];
    resource?: IResourceItem;
    priority?: number;
    relationInfer?: Field | null | false;
    canRelation?: boolean;
};

export type FieldType =
    | "relation"
    | "array"
    | "object"
    | "date"
    | "email"
    | "image"
    | "url"
    | "richtext"
    | "text"
    | "number"
    | "boolean"
    | "unknown"
    | `custom_${string}`
    | null;


export type Page = "index" | "show" | "edit" | "create";

export const findField = (fields: Field[], key: string) => fields.find((field) => field.key === key);